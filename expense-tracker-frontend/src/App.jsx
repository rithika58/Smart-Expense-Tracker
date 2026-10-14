import { useState } from "react";
import axios from "axios";

function App() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [expenses, setExpenses] = useState([]);

    const [title, setTitle] = useState("");
    const [amount, setAmount] = useState("");
    const [category, setCategory] = useState("");

    const handleLogin = async () => {

        try {

            const response = await axios.post(
                "http://localhost:8080/auth/login",
                {
                    email,
                    password
                }
            );

            localStorage.setItem("token", response.data);

            alert("Login Successful!");

        } catch (error) {

            console.log(error);

            alert("Login Failed!");
        }
    };

    const fetchExpenses = async () => {

        try {

            const token = localStorage.getItem("token");

            const response = await axios.get(
                "http://localhost:8080/expenses",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setExpenses(response.data);

        } catch (error) {

            console.log(error);

            alert("Failed to fetch expenses!");
        }
    };

    const addExpense = async () => {

        try {

            const token = localStorage.getItem("token");

            await axios.post(
                "http://localhost:8080/expenses",
                {
                    title,
                    amount,
                    category
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            alert("Expense Added!");

            fetchExpenses();

        } catch (error) {

            console.log(error);

            alert("Failed to add expense!");
        }
    };

    return (
        <div style={{ padding: "20px" }}>

            <h1>Login</h1>

            <input
                type="email"
                placeholder="Enter Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <br /><br />

            <input
                type="password"
                placeholder="Enter Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <br /><br />

            <button onClick={handleLogin}>
                Login
            </button>

            <br /><br />

            <button onClick={fetchExpenses}>
                Fetch Expenses
            </button>

            <hr />

            <h1>Add Expense</h1>

            <input
                type="text"
                placeholder="Enter Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />

            <br /><br />

            <input
                type="number"
                placeholder="Enter Amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />

            <br /><br />

            <input
                type="text"
                placeholder="Enter Category"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
            />

            <br /><br />

            <button onClick={addExpense}>
                Add Expense
            </button>

            <hr />

            {
                expenses.map((expense) => (
                    <div key={expense.id}>

                        <h3>{expense.title}</h3>

                        <p>{expense.amount}</p>

                        <p>{expense.category}</p>

                        <hr />

                    </div>
                ))
            }

        </div>
    );
}

export default App;