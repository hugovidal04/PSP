<!-- Trigonometría -->

<!-- Programa que muestra la función seno y coseno para 
valores comprendidos entre 0 y 2 con intervalor de 0.01
muestra los valores menores que 0.5 con distinto color
realiza un pautado de la tabla para facilitar su lectura-->

<!DOCTYPE html>
<html>
<head>
	<title>Tabla trigonométrica</title>
</head>
<body>
	<h1>Tabla trigonométrica</h1>
	<?php
		// Creamos función para mostrar valores
		function muestra($valor){
			if ($valor < 0.5) 
				$color = "red";
			else
				$color ="blue";
			echo"<td><font color='$color'>$valor</font></td>\n";
		}
	?>
	<table border="1">
		<tr bgcolor="#AAA"><th>Valor</th><th>Seno</th><th>Coseno</th></tr>
		<?php
			for ($x=0; $x<2; $x+=0.01) { //recorremos valores a mostrar

			 	if ($x * 100 % 2 == 0) { //aprovechamos valor de $x para alternar color de fila
			 		echo "<tr bgcolor='#CCC'>";	
			 	} 
			 	else {
			 		echo "<tr bgcolor='#FFF'>";
			 	}

		 		//Mostramos los diferentes valores de la tabla
			 	muestra($x);
			 	muestra(number_format(sin($x), 10));
			 	muestra(number_format(cos($x), 10));
			 	echo "</tr>";
			 } 
		 ?>
	</table>
</body>
</html>
