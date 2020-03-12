const express = require('express');
var app = express();

const session = require('express-session');
const formidable = require('formidable');
const fs = require('fs');
const util = require('util');
const nodemailer = require("nodemailer");
const crypto = require('crypto');

app.set('view engine', 'ejs');

// initializari socket.io
const http = require('http')
const socket = require('socket.io');
const server = new http.createServer(app);  
var  io = socket(server)
io = io.listen(server);//asculta pe acelasi port ca si serverul

//conectarea la logare
var conexiune_index
io.on("connection", (socket) => {  
    console.log("Conectare!");
	conexiune_index = socket
    socket.on('disconnect', () => {
		conexiune_index = null;
		console.log('Deconectare')
	});
}); 

//ia userii din fisier
function getJson(numeFis){
	let textFis = fs.readFileSync(numeFis);
	return JSON.parse(textFis); 
} 

//scriu JSON-ul in fisier (inlocuind datele vechi)
function saveJson(obJson, numeFis){
	let data = JSON.stringify(obJson); //transform in JSON
	fs.writeFileSync(numeFis, data);
}

app.use(express.static(__dirname));

serverPass = "tralala";

//setez o sesiune
app.use(session({
  secret: 'abcdefg', //folosit de express session pentru criptarea id-ului de sesiune
  resave: true,
  saveUninitialized: false
}));

async function trimiteMail(username, email) {
	let transporter = nodemailer.createTransport({
		service: 'gmail',

    secure: false,
    auth: {
      user: "valencicadobre@gmail.com", //(de aici se trimite catre user)
      pass: "test" 
    },
	    tls: {
        rejectUnauthorized: false//pentru gmail
    }
  });

  //trimitere mail
  let info = await transporter.sendMail({
    from: '"valencicadobre" <valencicadobre@gmail.com>',
    to: email,
    subject: "User nou", 
    text: "Salut, " + username + "te-ai inregistrat cu succes !" , 
    html: "<p>salut, " + username + "te-ai inregistrat cu succes !" +"</p>" 
  });

  console.log("Mesaj trimis: %s", info.messageId);
}

//--------------------------------------------------------------------------
app.get('/', function(req, res) {
    res.render('html/index', {user: req.session.username});
});

app.get('/paste', function(req, res) {
    res.render('html/paste', {user: req.session.username});
});

app.get('/craciun', function(req, res) {
    res.render('html/craciun', {user: req.session.username});
});
app.get('/martisor', function(req, res) {
    res.render('html/martisor', {user: req.session.username});
});
app.get('/sticle', function(req, res) {
    res.render('html/sticle', {user: req.session.username});
});
app.get('/paste', function(req, res) {
    res.render('html/paste', {user: req.session.username});
});
app.get('/floral', function(req, res) {
    res.render('html/floral', {user: req.session.username});
});
app.get('/comenzi', function(req, res) {
    
	let rawdata = fs.readFileSync('comenzi.json');
	let jsfis = JSON.parse(rawdata);

	res.render('html/comenzi',{comenzi:jsfis.comenzi, user: req.session.username});
});

app.get('/clienti', function(req, res) {

	let rawdata = fs.readFileSync('clienti.json');
	let jsfis = JSON.parse(rawdata);

	res.render('html/clienti',{clienti:jsfis.clienti, user: req.session.username});
});

//-----------------------------------------------------------------------------

app.post('/', function(req, res) {
	var form = new formidable.IncomingForm();
	form.parse(req, function(err, fields, files) {
		jsfis = getJson('clienti.json');
		var cifru = crypto.createCipher('aes-128-cbc', 'mypassword');
		var encrParola = cifru.update(fields.parola, 'utf8', 'hex');
		encrParola += cifru.final('hex'); //inchid cifrarea (altfel as fi putut adauga text nou cu update ca sa fie cifrat
		let user = jsfis.clienti.find(function(x){
			return (x.username == fields.username && x.parola == encrParola );
		});
		if(user){
			console.log(user);
			console.log(user.parola);
			console.log(encrParola);
			req.session.username = user; //setez userul ca proprietate a sesiunii
		}
		else
			res.status(500).send("Ati introdus datele gresit !");
		res.render('html/index',{user: req.session.username});
	});

});


app.get('/register', function(req, res) {
    res.redirect('/inregistrare_clienti');//la cererea paginii /register redirectez catre pagina inregistrare_user
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
app.get('/inregistrare_clienti', function(req, res) {
	console.log(req.session.username);
    res.render('html/inregistrare_clienti', {user: req.session.username});
});

app.post('/inregistrare_clienti', (req, res) => {
	var form = new formidable.IncomingForm();
	form.parse(req, function(err, fields, files) {
		let rawdata = fs.readFileSync('clienti.json');
		let jsfis = JSON.parse(rawdata);
		var cifru = crypto.createCipher('aes-128-cbc', 'mypassword');
		var encrParola = cifru.update(fields.parola, 'utf8', 'hex');
		encrParola += cifru.final('hex');

		jsfis.clienti.push({id:jsfis.lastId, username:fields.username, nume:fields.nume, email: fields.email, parola: encrParola, dataInreg: new Date(), rol:'client'});
		jsfis.lastId++;
		res.render('html/inregistrare_clienti', {user: req.session.username, rsstatus:"ok"});

		saveJson(jsfis,'clienti.json')
		trimiteMail(fields.username, fields.email).catch((err) => {console.log(err)})
    });

});

app.get('/admini', function(req, res) {
	res.render('html/admini', {user: req.session.username});
});

app.post('/admini', (req, res) => {
	var form = new formidable.IncomingForm();
	form.parse(req, function(err, fields, files) {
		let rawdata = fs.readFileSync('clienti.json');
		let jsfis = JSON.parse(rawdata);
		var cifru = crypto.createCipher('aes-128-cbc', 'mypassword');
		var encrParola = cifru.update(fields.parola, 'utf8', 'hex');
		encrParola += cifru.final('hex');
		jsfis.clienti.push({id:jsfis.lastId, username:fields.username, nume:fields.nume, email: fields.email, parola: encrParola, dataInreg: new Date(), rol:'admin'});
		jsfis.lastId++;
		res.render('html/admini', {user: req.session.username, rsstatus:"ok"});

		saveJson(jsfis,'clienti.json')
		trimiteMail(fields.username, fields.email).catch((err) => {console.log(err)})
    });

});

app.get('/formular_comanda', function(req, res) {
    res.render('html/formular_comanda', {user: req.session.username});
});

app.post('/formular_comanda', (req, res) => {
	var form = new formidable.IncomingForm();
	form.parse(req, function(err, fields, files) {
		let rawdata = fs.readFileSync('comenzi.json');
		let jsfis = JSON.parse(rawdata);
		jsfis.comenzi.push({nume:fields.nume, email: fields.email, telefon: fields.telefon, produs: fields.produs, aditional: fields.aditional, oras: fields.oras, adresa:fields.adresa, comments:fields.comments, plata: fields.plata});
		res.render('html/formular_comanda', {user: req.session.username, rsstatus:"ok"});

		saveJson(jsfis,'comenzi.json')
		trimiteMail(fields.username, fields.email).catch((err) => {console.log(err)})
    });
	
});


app.get('/logout', function(req, res) {
    req.session.destroy();
	res.render('html/logout');
});

app.use(function(req,res){
    res.status(404).render('html/404error');
});

//-------------------------------------------------------------------------
app.use('/css', express.static('css'));
app.use('/images', express.static('images'));
app.use('/videos', express.static('videos'));
server.listen(8080);
console.log('8080 is the magic port');