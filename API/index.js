const fs = require('fs');
const express = require('express');
var app = express();

app.use(express.json());

app.get('/', (req, res) => {
    res.json({ "what_are_you_looking":"at" })
});

app.listen(4000, console.log('http://localhost:4000'))