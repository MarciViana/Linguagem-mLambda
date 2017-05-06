println(1);

print("teste ");

println(12/6);

4 : a;

println(a);

println("concatenando string e valor ",2+2,a);



#fim dos testes de print



a : b;

println(b);

a+1 : a;

println("a: ",a," b: ",b);

load("Entre com um valor p load: ") : s;

println("o valor digitado foi: ", s);



#fim dos testes de variaveis
1 : a;

1 : b;

if b <= a and b==a{

	println("NOS SAO BOM D+");

}
else{

	println("FUDEU");
}

while b < 10{

	println(b);

	b + 1 : b;

}

if b >= a or b==a{

	println("NOS SAO BOM D+ MESMO");

}


while a<6 {

	println(a);
	a+1 : a;

}

#fim dos testes condicionais


load("Entre com o tamanho do vetor: ") : s;
new rand [s] : array;
new zero [s] : arra;
new fill [s,5] : arr;
array.at(s / 2) : medium;
array.size() : tam;

println(array);
println(arra);
println(arr);
if tam == s {

	println("parece q deu bom");

}else{
	println("etaaa ",s,"  ",tam, " meio:",medium);


}

#cria arrays e arrays q retornam inteiros

3 : s;
new rand [s] : array;
new zero [s] : arra;
new fill [s,5] : arr;
array.at(s / 2) : medium;
array.size() : tam;
arr.set(1,3) : arr;
println(array);
println(arra);
println(arr);
if tam != 1  {

	println("parece q deu bom");

}else{
	println("etaaa ",s,"  ",tam, " meio:",medium);


}

#o set funciona


3 : s;
new rand [s] : array;
new zero [s] : arra;
new fill [s,5] : arr;

arr.add(3) : arr;
arra.add(arr) : arra;
println(array);
println(arra);
println(arr);

#o add tb

3 : s;
new rand [s] : array;
new zero [s] : arra;
new fill [s,5] : arr;
println(array);
arr.add(3) : arr;
array.sort() : arra;
println(array);
println(arra);
println(arr);

# e o sort

# Generate a random vector with size s.
load("Entre com o tamanho do vetor: ") : s;
new rand [s] : array;
println(array);
# Find the medium.
array.sort().at(s / 2) : medium;

# Find the elements lower (left) and higher (right) then the medium.
array.filter(n -> n < medium).size() : left;
array.filter(n -> n > medium).size() : right;

# Print the results.
println("Existe(m) " , left , " valor(es) menor(es) e " , right ,
        " valor(es) maior(es) que a mediana " , medium);

#e filter
