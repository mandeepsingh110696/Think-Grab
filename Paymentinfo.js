var express=require('express');
const app = express();
var mysql= require('mysql');

var bodyparser= require('body-parser');

var connection = mysql.createConnection({
    host:"localhost",
    user:"root",
    password:"",
    database:"thinkandgrab"
   
   });

   app.use(bodyparser.json());

   app.use(bodyparser.urlencoded({extended:true}));

   app.post('/paymentinfo/',(req,res,next)=>{
   var data= req.body;
   var cardno = data.cardno;
   var expdate = data.expdate;
   var cvv = data.cvv;
   var postalcode= data.postalcode;
   var countrycode= data.countrycode;
   var mobileno= data.mobileno;
   
  

   
   

   console.log(cardno+""+expdate+""+cvv+""+postalcode+""+countrycode+""+mobileno);

        
            var insertt= "INSERT INTO paymentinfo (cardno, expdate ,cvv,postalcode,countrycode,mobileno) values(?,?,?,?,?,?)";
            values=[cardno,expdate,cvv,postalcode,countrycode,mobileno];
            console.log("executing:"+ insertt);
            connection.query(insertt,values,(error,result,fields)=>{
                connection.on('error',(error)=>{
                    console.log("[Mysql error]",error);
          
                  });
                  res.json("Payment Information added successfully");
                  console.log("Payment Information added successfully");


            });

   });
   var server=app.listen(4000,()=>{
    console.log("Server running at http://localhost:4000");
   });