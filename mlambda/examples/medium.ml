# Generate a random vector with size s.
load("Entre com o tamanho do vetor: ") : s;
new rand [s] : array;

# Find the medium.
array.sort().at(s / 2) : medium;

# Find the elements lower (left) and higher (right) then the medium.
array.filter(n -> n < medium).size() : left;
array.filter(n -> n > medium).size() : right;

# Print the results.
println("Existe(m) " , left , " valor(es) menor(es) e " , right ,
        " valor(es) maior(es) que a mediana " , medium);
