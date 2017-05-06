# Load the vector with user input.
load("Entre com o tamanho do vetor: ") : s;
new zero [s] : array;
0 : i;
array.apply(n -> i + 1 : i; load("Valor " , i , ": ") : n;);
array.show();
# Count the odds and even.
0 : even, odd;
array.each(n -> if n % 2 == 0 {
  even + 1 : even;
} else {
  odd + 1 : odd;
});
array.show();
# Print the result$~
println("Voce entrou com " , even , " par(es) e " , odd , " impar(es)");
