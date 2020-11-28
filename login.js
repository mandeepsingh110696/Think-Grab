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


//login api
app.post('/signin/',(req,res,next)=>{
    var data= req.body;
    var email = data.email;
    var password= data.password;
    connection.query("SELECT * FROM customerregister  WHERE email = ?" ,[email],function(error,result,fields){
        connection.on('error',(error)=>{
          console.log("[Mysql error]",error);

        });

        if(result && result.length){
            console.log(result);

            if(password==result[0].password)
            {
                res.json("User logged in");
                res.end;
            }
            else{
                res.json("wrong password");
                res.end;
            }


          }
          else{
            res.json("User not found");
            res.end;
          }

        });


    });


   var server=app.listen(4000,()=>{
    console.log("Server running at http://localhost:4000");
   });