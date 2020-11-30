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

   app.post('/homeproducts/',(req,res,next)=>{
   var data= req.body;
   var name = data.name;
   var description = data.description;
   var picture= data.picture;
   var price= data.price;
   
   

   console.log(name+""+description+""+picture+""+price);

        
            var insertt= "INSERT INTO products (name, description ,picture,price) values(?,?,?,?)";
            values=[name,description,picture,price];
            console.log("executing:"+ insertt);
            connection.query(insertt,values,(error,result,fields)=>{
                connection.on('error',(error)=>{
                    console.log("[Mysql error]",error);
          
                  });
                  res.json("Product Added");
                  console.log("Product Added Successfully");


            });

   });
   var server=app.listen(4000,()=>{
    console.log("Server running at http://localhost:4000");
   });