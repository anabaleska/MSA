import React, {useEffect, useState} from 'react';

const List = () => {

    const [tickers, setTickers] = useState([]);
    const [details, setDetails] = useState([]);

    useEffect(() => {

        fetch('http://localhost:8081/api/tickers')
            .then(response => response.json())
            .then(data => {
                console.log("Tickers fetched:", data);
                setTickers(data.content || []);
            })
            .catch(error => console.error("Error fetching tickers:", error));


        fetch('http://localhost:8081/api/ticker-values')
            .then(response => response.json())
            .then(data => {
                console.log("Details fetched:", data);
                setDetails(data.content || []);
            })
            .catch(error => console.error("Error fetching details:", error));
    }, []);

    return (
        <div>
            <h1>Tickers</h1>
            {tickers.length > 0 ? (
                tickers.map((ticker, index) => (
                    <div key={ticker.id || index}> {/* Unique key */}
                        {ticker.tickerName}
                    </div>
                ))
            ) : (
                <p>No tickers available</p>
            )}

            <h1>Details</h1>
            {details.length > 0 ? (
                details.map((detail, index) => (
                    <div key={detail.id || index}> {/* Unique key */}
                        {detail.maxPrice}
                    </div>
                ))
            ) : (
                <p>No details available</p>
            )}
        </div>
    );
};

export default List;