import React, {useEffect} from 'react';
import { useState } from "react";
import GraphComponent from "./components/indicators/GraphComponet";
import Dropdown from "./components/indicators/Dropdown";
import styles from "./Home.module.css";
import TickerList from "./components/news/TickerList";

const Home = () => {
    const [tickers, setTickers] = useState([]);
    const [selectedTicker, setSelectedTicker] = useState("");
    console.log(styles)

    // Fetch tickers from the backend API
    useEffect(() => {
        fetch('http://localhost:8081/api/tickers')
            .then(response => response.json())
            .then(data => {
                console.log("Tickers fetched:", data);
                setTickers(data.content || []); // Extract content property
            })
            .catch(error => console.error("Error fetching tickers:", error));

    }, []);


    const handleSelect = (value) => {
        setSelectedTicker(value);
        console.log("Selected:"+ value);
        console.log(styles);

    };

    return (
        <div className={styles.dashboard}>
            <h1>Dashboard</h1>
            <Dropdown options={tickers ? tickers : []} onSelect={handleSelect} />
            <div className={styles.layout}>
            <GraphComponent tickerId={selectedTicker} />
                <TickerList /> </div>
        </div>

    );
};

export default Home;