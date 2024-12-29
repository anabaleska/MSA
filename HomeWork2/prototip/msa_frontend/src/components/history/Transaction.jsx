import * as React from "react";
import styles from './Transaction.module.css';

export default function Transaction({ transactionData, onDelete, onEdit }) {
    function formatPrice(price) {
        if (price == null) return "/";
        return price.toLocaleString('de-DE', {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
        });
    }
    return (
        <div className={styles.transactionRow}>
            <div className={styles.transactionContent}>
                <div className={styles.transactionDetails}>
                    <div className={styles.transactionCode}>{transactionData.tickerName}</div>
                    <div className={styles.transactionAmount}>{transactionData.maxPrice == null ? "/" : formatPrice(transactionData.maxPrice)}</div>
                    <div className={styles.transactionBalance}>{transactionData.minPrice == null ? "/" : formatPrice(transactionData.minPrice)}</div>
                    <div className={styles.transactionDate}>{transactionData.date.split("T")[0]}</div>
                    <div className={styles.transactionTotal}>{formatPrice(transactionData.lastTransactionPrice)}</div>
                </div>
            </div>
            <img
                loading="lazy"
                src="https://cdn.builder.io/api/v1/image/assets/TEMP/870cf40761e55ecc3999d14dc9f9abe4673432c3e5fb1a077cca9b5c4a97353b?placeholderIfAbsent=true&apiKey=60217a24597e419ca472b84d3fa7f5bd"
                className={styles.divider}
                alt=""
            />
        </div>
    );
}