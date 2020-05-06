const fs = require('fs');
const express = require('express');
var app = express();

app.use(express.json());

app.get('/', (req, res) => {
    res.json({ "what_are_you_looking":"at" })
});

app.get('/get_users', (req, res) => {
    fs.readFile(__dirname + "/store/bans.json", 'utf8', function readFileCallback(err, data){
        d = JSON.parse(data);
        res.json(d)
    });
});

app.post('/add_user', (req, res) => {
    var { name, uuid, reason, banned_by } = req.body;
    if(!name || !uuid || !reason || !banned_by) {
        return res.json({ error: "Missing arguments!"})
    }
    fs.readFile(__dirname + "/store/bans.json", 'utf8', (err, data) => {
        if (err){
            console.log(err);
        } else {
        obj = JSON.parse(data);
        obj.push({ banned_user: name, banned_uuid: uuid, ban_reason: reason, banned_by: banned_by }) //add some data
        json = JSON.stringify(obj);
        fs.writeFile(__dirname + "/store/bans.json", json, () => {});
    }});
        res.json({ success: "true"})
})

app.listen(4000, console.log('http://localhost:4000'))