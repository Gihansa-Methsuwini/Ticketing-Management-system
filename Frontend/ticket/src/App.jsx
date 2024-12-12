// Import necessary modules
import React, { useState } from 'react';
import './App.css'; // Add custom styles here

function App() {
  const [customerName, setCustomerName] = useState('');
  const [customerId, setCustomerId] = useState('');
  const [customerRate, setCustomerRate] = useState('');
  const [releaseRate, setReleaseRate] = useState('');
  const [totalTickets, setTotalTickets] = useState('');
  const [vendorsCount, setVendorsCount] = useState(0);
  const [customers, setCustomers] = useState([]);

  const handleAddCustomer = () => {
    if (customerName) {
      setCustomers([...customers, { id: customers.length + 1, name: customerName }]);
      setCustomerName('');
    }
  };

  const handleStopCustomer = () => {
    if (customerId) {
      setCustomers(customers.filter(customer => customer.id !== parseInt(customerId)));
      setCustomerId('');
    }
  };

  const handleAddVendor = () => {
    if (totalTickets) {
      setVendorsCount(vendorsCount + 1);
      setTotalTickets('');
    }
  };

  return (
    <div className="app" style={{ backgroundColor: 'white', minHeight: '100vh', padding: '20px', textAlign: 'center' }}>
      <h1>Event Management Ticketing System</h1>
      <h2 style={{ fontFamily: 'Arial, sans-serif', fontSize: '30px', color: 'black' }}>Welcome to Event Management System!</h2>
      <p style={{ fontFamily: 'Arial, sans-serif', color: 'black', fontSize: '20px' }}>Vendors count: {vendorsCount}</p>

      <div className="form-group">
        <input
          type="text"
          placeholder="Enter customer name"
          value={customerName}
          onChange={(e) => setCustomerName(e.target.value)}
        />
        <button onClick={handleAddCustomer}>Add customer</button>
      </div>

      <div className="form-group">
        <input
          type="text"
          placeholder="Enter customer ID"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
        />
        <button onClick={handleStopCustomer}>Stop Customer</button>
      </div>

      <div className="form-group">
        <input
          type="text"
          placeholder="Enter customer rate"
          value={customerRate}
          onChange={(e) => setCustomerRate(e.target.value)}
        />
        <button>Customer Rate</button>
      </div>

      <div className="form-group">
        <input
          type="text"
          placeholder="Enter Release Rate"
          value={releaseRate}
          onChange={(e) => setReleaseRate(e.target.value)}
        />
        <button>Release Rate</button>
      </div>

      <div className="form-group">
        <input
          type="text"
          placeholder="Enter Total Tickets"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
        />
        <button onClick={handleAddVendor}>Add Vendor</button>
      </div>

      <div style={{ display: 'flex', justifyContent: 'center', gap: '10px', marginTop: '20px' }}>
        <button onClick={handleAddCustomer}>Start</button>
        <button onClick={handleStopCustomer}>Stop</button>
      </div>
    </div>
  );
}

export default App;