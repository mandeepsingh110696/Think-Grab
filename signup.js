//signup node js file
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

   app.post('/signup/',(req,res,next)=>{
   var data= req.body;
   var email = data.email;
   var fullname = data.fullname;
   var password= data.password;
   var confirmpassword= data.confirmpassword;
   
   

   console.log(email+""+fullname+""+password+""+confirmpassword);

   connection.query("SELECT * FROM customerregister WHERE email = ?" ,[email],function(error,result,fields){
        connection.on('error',(error)=>{
          console.log("[Mysql error]",error);

        });
        if(result && result.length){
          res.json("User already exists");
        }
        else{
            var insertt= "INSERT INTO customerregister (email,fullname,password,confirmpassword) values(?,?,?,?)";
            values=[email,fullname,password,confirmpassword];
            console.log("executing:"+ insertt);
            connection.query(insertt,values,(error,result,fields)=>{
                connection.on('error',(error)=>{
                    console.log("[Mysql error]",error);
          
                  });
                  res.json("User registered");
                  console.log(" User Registartion Successful");


            });


        }



   });




   });



   var server=app.listen(4000,()=>{
    console.log("Server running at http://localhost:4000");
   });