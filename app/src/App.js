import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import DatePicker from "react-datepicker";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import "react-datepicker/dist/react-datepicker.css";

class App extends Component {
	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.state = {
			startDate: new Date("10.10.2010"),
			amount: 101,
			result:""
		};
		this.handleDateChange = this.handleDateChange.bind(this);
		this.handleAmountChange = this.handleAmountChange.bind(this);
	}


	async remove(id, date) {
        await fetch(`/api/usd?amount=${id}&date=${date}`, {
          method: 'GET',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }).then(response => response.json())
        .then((data) => {
          this.setState({result: data});
        });
    }

	handleDateChange(date) {
		this.setState({
			startDate: date
		});
	}

	handleAmountChange(event) {
		this.setState({
			amount: event.target.value
		});
	}

	formatDate(date) {
		return date.toISOString();
	}

	handleSubmit(e) {
	    this.remove(this.state.amount, this.formatDate(this.state.startDate));
		e.preventDefault();
	}

  render() {
    return (
	<div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
		<form onSubmit={this.handleSubmit}>
        <label>
          Amount:
          <input type="text" value={this.state.amount} onChange={this.handleAmountChange}/>
        </label>
		<br/>
		Date:
        <DatePicker
            selected={this.state.startDate}
            onChange={this.handleDateChange}
            dateFormat="dd.MM.yyyy"
        />
		<br/>
        <input type="submit" value="Recalculate" />
		<br/>
		<input type="text" placeholder='result' disabled={true} value={this.state.result} />
      </form>
	</div>
    );
  }
}

export default App;
