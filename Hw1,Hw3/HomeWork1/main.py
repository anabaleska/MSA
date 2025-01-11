import time
from filter_one import extract_tickers, save_tickers_to_db,create_ticker_table
from filter_three import process_tickers

def run_pipeline():
    start_time = time.time()

    tickers = extract_tickers()
    save_tickers_to_db(tickers)
    create_ticker_table()

    process_tickers(tickers)
    print("Historical data has been updated.")
    end_time = time.time()

    total_time = end_time - start_time
    print(f"Data pipeline completed in {total_time:.2f} seconds.")

if __name__ == "__main__":
    run_pipeline()
