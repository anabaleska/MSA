import psycopg2
import requests
from bs4 import BeautifulSoup
from transformers import pipeline
from config import DB_CONFIG

def get_news_pages():
    for page_num in range(1, 6):
        url = f"https://www.mse.mk/mk/news/latest/{page_num}"

        news_links = []

        response = requests.get(url)
        if response.status_code != 200:
            print(f"Failed to fetch the page: {url}")
            continue

        soup = BeautifulSoup(response.content, "html.parser")
        panel_body = soup.find("div", class_="panel-body")
        if panel_body:
                links = panel_body.find_all("a", href=True)
                news_links.extend([link["href"] for link in links])

        if not news_links:
            print("No news links found.")
            return None
        return news_links

def fetch_news(news_links, ticker, ticker_id, full_name):

    if not news_links:
        print("No news links available.")
        return
    sentiment_analyzer=pipeline("sentiment-analysis", model="agentlans/multilingual-e5-small-aligned-sentiment")
    company_news_found = False


    with psycopg2.connect(**DB_CONFIG) as conn:
        with conn.cursor() as cursor:
            pos, neg, neutral = 0, 0, 0
            for index, link in enumerate(news_links):
                print(f"Index: {index}, Link: {link}")
                if not link.startswith("http"):
                    link = f"https://www.mse.mk{link}"

                response = requests.get(link)
                if response.status_code != 200:
                    print(f"Failed to fetch news article: {link}")
                    continue

                soup = BeautifulSoup(response.content, "html.parser")
                news_text = soup.find("div", class_="panel-body")
                if news_text:
                    news_text=news_text.get_text()
                else:
                    print("No news text found.")
                    continue

                if ticker.lower() in news_text.lower() or full_name.lower() in news_text.lower() :
                    company_news_found = True
                    sentiment_result = sentiment_analyzer(news_text[:512])[0]
                    print(f"Sentiment result for link {link}: {sentiment_result}")

                    sentiment_label = sentiment_result["label"]

                    if sentiment_label=='LABEL_0':
                            neg+=1
                    elif sentiment_label=='LABEL_1':
                            neutral+=1
                    else:
                            pos+=1

            if not company_news_found:
                print("No information.")
                return
            else:
                if pos/(pos+neg+neutral) >0.7:
                        prediction="Buy"
                elif neg/(pos+neg+neutral) >0.7:
                        prediction="Sell"
                else :
                        prediction="Hold"

                cursor.execute("""
                                               INSERT INTO tickers_news (ticker_id, news_id,date,prediction)
                                               VALUES (%s, %s, CURRENT_DATE, %s);
                                           """, (ticker_id, index+100,prediction))

                conn.commit()



def fetch_tickers(conn):
    """Fetch all stock tickers and their full names."""
    with conn.cursor() as cursor:
        cursor.execute("SELECT id, ticker, full_name FROM tickers;")
        return cursor.fetchall()

def get_latest_news_date(conn):
    """Fetch the latest news date from the database."""
    with conn.cursor() as cursor:
        cursor.execute("SELECT MAX(date) FROM latest_news;")
        return cursor.fetchone()[0]

def fetch_and_process_news(conn):
    """Fetch and process the latest news articles."""
    tickers = fetch_tickers(conn)
    latest_date = get_latest_news_date(conn)
    news_links=get_news_pages()
    for ticker_id, ticker_name, full_name in tickers:
        fetch_news(news_links,  ticker_name, ticker_id, full_name)
        print(f"done for ticker {ticker_name}")

def init_db():
    """Initialize the database and create necessary tables."""
    with psycopg2.connect(**DB_CONFIG) as conn:
        with conn.cursor() as cursor:
            cursor.execute("""
                CREATE TABLE IF NOT EXISTS tickers_news (
                    id SERIAL PRIMARY KEY,
                    ticker_id INT,
                    news_id INT,
                    date DATE,
                    prediction VARCHAR(255),
                    FOREIGN KEY (ticker_id) REFERENCES tickers(id)
                );
            """)
            conn.commit()
            print('Database tables created if they didn’t exist.')

def init():
    """Main entry point to fetch and store news."""
    print("Fetching latest news...")

    try:
        with psycopg2.connect(**DB_CONFIG) as conn:
            init_db()  
            fetch_and_process_news(conn)

    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    init()

