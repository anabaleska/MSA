from .sentiment import init
from .indicators import process_all_tickers
from .fetch_full_names import main

if __name__ == '__main__':
    main()
    process_all_tickers()
    init()