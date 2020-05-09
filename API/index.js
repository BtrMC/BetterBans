const fs = require('fs');
const express = require('express');
const sa = require('superagent');
var app = express();

app.use(express.json());

app.get('/', (req, res) => {
    res.json({ "what_are_you_looking":"at" });
});

app.get('/get_users', (req, res) => {
    fs.readFile(__dirname + "/store/bans.json", 'utf8', function readFileCallback(err, data) {
        d = JSON.parse(data);
        res.json(d);
    });
});

app.get('/name/:name', (req, res) => {
    var name = req.params.name;
    fs.readFile(__dirname + "/store/bans.json", 'utf8', function readFileCallback(err, data) {
        d = JSON.parse(data);
        for (var i = 0; i < d.length; i++) {
            if (d[i].banned_user == name) {
                return res.json(d[i]);
            }
        } res.json({ success: 'true'});
    });
});

app.get('/uuid/:uuid', (req, res) => {
    var uuid = req.params.uuid;
    fs.readFile(__dirname + "/store/bans.json", 'utf8', function readFileCallback(err, data) {
        d = JSON.parse(data);
        for (var i = 0; i < d.length; i++){
            if (d[i].banned_uuid == uuid){
                res.json(d[i]);
            }
        }
    });
});

app.get('/ban', async (req, res) => {
    var name = req.query.name;
    var reason = req.query.reason;
    var banned_by = req.query.banner;
    if(!name || !reason || !banned_by) {
        return res.json({ error: "Missing arguments!"});
    }
    const { body } = await sa.get(`https://api.mojang.com/users/profiles/minecraft/${name}`);
    if(!body.id) {
        res.json({ error: "Invalid Username"});
        return;
    }
    var uuid = body.id;
    fs.readFile(__dirname + "/store/bans.json", 'utf8', (err, data) => {
        if (err){
            console.log(err);
        } else {
        obj = JSON.parse(data);
        obj.push({ banned_user: name, banned_uuid: uuid, ban_reason: reason, banned_by: banned_by }); //add some data
        json = JSON.stringify(obj);
        fs.writeFile(__dirname + "/store/bans.json", json, () => {});
    }});
        res.json({ success: "true"});
});

app.get('/remove/:user', async (req, res) => {
    var name = req.params.user;
    var data = fs.readFileSync(__dirname + '/store/bans.json');
    var json = JSON.parse(data);
    json = json.filter((json) => { return json.banned_user !== name });
    fs.writeFileSync(__dirname + '/store/bans.json', JSON.stringify(json, null, 2));
    res.json({ success: 'true' });
});

app.listen(4000, console.log('http://localhost:4000'))