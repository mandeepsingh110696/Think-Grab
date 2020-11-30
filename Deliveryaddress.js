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

   app.post('/deliveryaddress/',(req,res,next)=>{
   var data= req.body;
   var city = data.city;
   var street = data.street;
   var aptno = data.aptno;
   var postalcode= data.postalcode;
   var province= data.province;
   var landmark = data.landmark;
   var name = data.name;
   var mobileno= data.mobileno;
   var alterno= data.alterno;
  

   
   

   console.log(city+""+street+""+aptno+""+postalcode+""+province+""+landmark+""+name+""+mobileno+""+alterno);

        
            var insertt= "INSERT INTO deliveryaddress (city, street ,aptno,postalcode,province,landmark,name,mobileno,alterno) values(?,?,?,?,?,?,?,?,?)";
            values=[city,street,aptno,postalcode,province,landmark,name,mobileno,alterno];
            console.log("executing:"+ insertt);
            connection.query(insertt,values,(error,result,fields)=>{
                connection.on('error',(error)=>{
                    console.log("[Mysql error]",error);
          
                  });
                  res.json("Delivery Address added successfully");
                  console.log("Delivery Address added successfully");


            });

   });
   var server=app.listen(4000,()=>{
    console.log("Server running at http://localhost:4000");
   });