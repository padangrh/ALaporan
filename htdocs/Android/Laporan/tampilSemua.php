<?php 
 
 /*
 
 penulis: Muhammad yusuf
 website: http://www.kodingindonesia.com/
 
 */
 
	$Tanggal1 = $_GET['tanggal1'];
	$Tanggal2 = $_GET['tanggal2'];
	//echo $Tanggal1;
	//echo $Tanggal2;
	
	$Tanggal1 = base64_decode($Tanggal1);
	$Tanggal2 = base64_decode($Tanggal2);
	//echo $Tanggal1;
	//echo $Tanggal2;
	
	$Queries = "SELECT Source, Tanggal, sum(Total) as Total FROM v_all";
	
	if ($Tanggal1 == "" AND $Tanggal2 == "") {
	}elseif ($Tanggal1 == "" OR $Tanggal2 == "") {
		if ($Tanggal2 <> "") {
			if ($Tanggal1 == "") {
				$Tanggal1 = $Tanggal2;
			}
		}
		$Queries = $Queries . " WHERE Tanggal = '@Tanggal1'"; 
	}else{
		$Queries = $Queries . " WHERE Tanggal >= '$Tanggal1' AND Tanggal <= '$Tanggal2'"; 
	}
	
	$Queries = $Queries . " GROUP BY Source";
	
	//Import File Koneksi Database
	require_once('koneksi.php');
	
	//Membuat SQL Query
	//$sql = "SELECT Source, Tanggal, sum(Total) as Total FROM v_all group by Source";
	$sql = $Queries;
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"Source"=>$row['Source'],
			"Tanggal"=>$row['Tanggal'],
			"Total"=>$row['Total']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>