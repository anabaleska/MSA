import React, { useEffect, useState } from "react";
import axios from "axios";
import styles from './TickerList.module.css';

const TickerList = () => {
    const [tickers, setTickers] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 5;

    useEffect(() => {

        axios.get("http://localhost:8081/api/ticker-news")
            .then(response =>
            {

                const data = response.data.content || [];
                console.log("Tickers fetched:", data);
                setTickers(data);
            })
            .catch(error => console.error("Error fetching tickers:", error));
    }, []);

    const tickerNameMap = {
        "5847": "ALK",
        "5881": "GECK",
        "5885": "GRNT",
        "5905": "KMB",
        "5907": "KOMU",
        "5917": "LOZP",
        "5921": "MB",
        "5927": "MPT",
        "5952": "REPL",
        "5956" : "RZIT",
        "5959" : "RZLV",
        "5961" : "RZUG",
        "5962" : "RZUS",
        "6001" : "VITA",
        "5882" : "GECT",
        "5893" : "INOV",
        "5929" : "MTUR",
        "5955" : "RZEK",
        "5963" : "SBT",
        "5966" : "SKON",
    };

    const displayedTickers = tickers.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
    );

    const nextPage = () => setCurrentPage(prev => Math.min(prev + 1, Math.ceil(tickers.length / itemsPerPage)));
    const prevPage = () => setCurrentPage(prev => Math.max(prev - 1, 1));

    return (
        <div id={"news"} className={styles.tickerList}>
            <h2>Ticker Predictions from Fundamental Analysis</h2>
            <table>
                <thead>
                <tr>
                    <th>TickerId</th>
                    <th>Date</th>
                    <th>Prediction</th>
                </tr>
                </thead>
                <tbody>
                {displayedTickers.map((ticker, index) => (
                    <tr key={index}>
                        <td>{tickerNameMap[ticker.tickerId]}</td>
                        <td>{ticker.date.split("T")[0]}</td>
                        <td className={styles.sentiment}>{ticker.sentiment}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div className={styles.pagination}>
                <button onClick={prevPage} disabled={currentPage === 1}>Previous</button>
                <button onClick={nextPage} disabled={currentPage === Math.ceil(tickers.length / itemsPerPage)}>Next</button>
            </div>
        </div>
    );
};

export default TickerList;
