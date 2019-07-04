//Q412
var fizzBuzz = function(n) {
    var res=[];
    for(var i=1;i<=n;i++){
        if(i%3==0&&i%5==0){
            res[i-1]='FizzBuzz';
        }else if(i%3==0){
            res[i-1]='Fizz';
        }else if(i%5==0){
            res[i-1]='Buzz';
        }else {
            res[i-1]=i.toString();
        }
    }
    return res;
};

var a=fizzBuzz(15);
console.log(a);