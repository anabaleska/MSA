import React, { useState } from 'react';
import { Input } from './components/login/Input';
import styles from './LoginPage.module.css';
import { useNavigate } from 'react-router-dom';
import { login } from './service/UserService';
import { jwtDecode } from 'jwt-decode';



export const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    const handleSignupClick = () => {
        navigate('/sign-up');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userLoginDTO = {
            email,
            password,
        };

        try {

            const response = await login(userLoginDTO);

            const token = localStorage.getItem('token');
            const decodedToken = jwtDecode(token);

            if (decodedToken.role === 'ADMIN') {
                navigate('/admin');
            } else {
                navigate('/');
            }
        } catch (error) {

            setErrorMessage('Login failed. Please check your credentials or try again later.');
            console.error('Login failed:', error);
        }
    };

    return (
        <div className={styles.container}>
            <div className={styles.content}>
                <h1 className={styles.title}>
                    Macedonian
                    <br />
                    <span className={styles.highlight}>Stock</span> Analysis
                </h1>

                <h2 className={styles.loginTitle}>Login</h2>

                {/* Display error message if there's any */}
                {errorMessage && <p className={styles.error}>{errorMessage}</p>}

                {/* Login Form */}
                <form onSubmit={handleSubmit} className={styles.form}>
                    {/* Controlled Input for Email */}
                    <Input
                        label="Email"
                        value={email}
                        onChange={setEmail}
                        type="email"
                        required
                        placeholder="example@gmail.com"
                    />

                    {/* Controlled Input for Password */}
                    <Input
                        label="Password"
                        value={password}
                        onChange={setPassword}
                        type="password"
                        required
                        placeholder="Enter your password"
                    />

                    {/* Submit Button */}
                    <button type="submit" className={styles.loginButton}>
                        Login
                    </button>

                    {/* Sign Up Link */}
                    <button
                        type="button"
                        className={styles.signupLink}
                        onClick={handleSignupClick}
                    >
                        Don't have an account? <span className={styles.signupText}>Sign Up</span>
                    </button>
                </form>
            </div>
        </div>
    );
};
