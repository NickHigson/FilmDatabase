function ajaxPost(address, data, responseHandler) {
  var request = getRequestObject();
  request.onreadystatechange = 
    function() { responseHandler(request); };
  request.open("POST", address, true);
  request.setRequestHeader("Content-Type", 
                           "application/x-www-form-urlencoded");
  request.send(data);
}

function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}

//gets the data format

function getFormat() {
  if(document.getElementById('format2').checked) {
  	return document.getElementById('format2').value;
  }
   else if(document.getElementById('format3').checked) {
  	return document.getElementById('format3').value;
  	}
  	else{
  	return "json";
  	}
 }

//Get data to either search for film, or update one
function getFilm() {
  return(escape(document.getElementById("film").value));
}

function getTitle() {
  return(escape(document.getElementById("title").value));
}

function getId() {
  return(escape(document.getElementById("id").value));
}

function getYear() {
  return(escape(document.getElementById("year").value));
}

function getDirector() {
  return(escape(document.getElementById("director").value));
}

function getStars() {
  return(escape(document.getElementById("stars").value));
}

function getReview() {
  return(escape(document.getElementById("review").value));
}


//Get data for inserting a film

function getTitleInsert() {
  return(escape(document.getElementById("titleInsert").value));
}

function getYearInsert() {
  return(escape(document.getElementById("yearInsert").value));
}

function getDirectorInsert() {
  return(escape(document.getElementById("directorInsert").value));
}

function getStarsInsert() {
  return(escape(document.getElementById("starsInsert").value));
}

function getReviewInsert() {
  return(escape(document.getElementById("reviewInsert").value));
}

//Get ID from delete modal
function getIdDelete() {
  return(escape(document.getElementById("idDelete").value));
}

//Series of string creators to be parsed by client
function makeTitleString() {
  var titleString =
    "title=" + getFilm() + 
    "&format=" + getFormat();
  return(titleString);
}

function makeDeleteString() {
  var idString =
    "id=" + getIdDelete()
  return(idString);
}

function makeFullString() {
	var fullString =
		"title=" + getTitle() +
		"&id=" + getId() +
		"&year=" + getYear() +
		"&director=" + getDirector() +
		"&stars=" + getStars() +
		"&review=" + getReview();
	return(fullString);
}

function makeInsertString() {
	var insertString =
		"title=" + getTitleInsert() +
		"&year=" + getYearInsert() +
		"&director=" + getDirectorInsert() +
		"&stars=" + getStarsInsert() +
		"&review=" + getReviewInsert();
	return(insertString);
}



function makeFormatString() {
  var formatString = "format=" + getFormat();
  return(formatString);
}

//Series of functions to determine datatype, therefore which functions to use
function getAllFilms() {
	format = getFormat();
	console.log(format);
	if (format==="xml"){
		getAllFilmsXML();
	}
	else if(format==="text"){
		getAllFilmsText();
	}
	else if(format==="json"){
		getAllFilmsJSON();
	}
		else{
		getAllFilmsJSON();
	}
}

function getFilmByTitle() {
	format = getFormat();
	console.log(format);
	if (format==="xml"){
		getFilmXML();
	}
	else if(format==="json"){
		getFilmJSON();
	}
		else if(format==="text"){
		getFilmText();
	}
	else{
		getFilmJSON();
	}
}

//Get All Films implentations
function getAllFilmsJSON() {
	var format = makeFormatString();
	
	jQuery.ajax({
		type:"GET",
		url:"show-all-films",
		data:format,
		success:function(data){
			var json = JSON.parse(data);
			$('#result-area').empty();
			if(json != null){
		    for (var i = 0; i < json.length; i++) {
				var id = json[i].id;
				var title = json[i].title;
				var year = json[i].year;
				var director = json[i].director;
				var stars = json[i].stars;
				var review = json[i].review;
			    $('#result-area').append("<li>Film ID: "+id + "<br>Title: " + title + "<br>Year: " + year + "<br>Director: " + director +"<br>Stars: " + stars +"<br>Review: " + review+"</li>");
		    }
		}
		}

	})
}

function getAllFilmsText() {
	var format = makeFormatString();
	jQuery.ajax({
		type:"GET",
		url:"show-all-films",
		data:format,
		success:function(data){
			$('#result-area').empty();
			$('#result-area').append(data);
		}
	})
}

function getAllFilmsXML() {
	format = makeFormatString();
	jQuery.ajax({
		type:"GET",
		url:"show-all-films",
		data:format,
		success:function(data){
			$('#result-area').empty();
			if(data != null){
				$(data).find('film').each(function(){
				var id = $(this).find('id').text();	
				var title = $(this).find('title').text();
				var year = $(this).find('year').text();
				var director = $(this).find('director').text();
				var stars = $(this).find('stars').text();
				var review = $(this).find('review').text();
				$('#result-area').append("<li>Film ID: "+id+" <br> Title: "+title+" <br>Year: "+year+" <br>Director: "+director+" <br>Stars: "+stars+" <br>Review: "+review+"</li>");
			});
			}
		}
	})
}


//Get film implementations
function getFilmJSON() {
		var format = makeTitleString();
	
		jQuery.ajax({
			type:"GET",
			url:"show-film",
			data:format,
			success:function(data){
				$('#result-area').empty();
				var json = JSON.parse(data);
				if(json != null){
			    for (var i = 0; i < json.length; i++) {
					var id = json[i].id;
					var title = json[i].title;
					var year = json[i].year;
					var director = json[i].director;
					var stars = json[i].stars;
					var review = json[i].review;
			       $('#result-area').append("<li>Film ID: "+id + "<br>Title: " + title + "<br>Year: " + year + "<br>Director: " + director +"<br>Stars: " + stars +"<br>Review: " + review+"</li>");
			    }
			}
			}
	
		})
}

function getFilmXML() {
	format = makeTitleString();
	jQuery.ajax({
		type:"GET",
		url:"show-film",
		data:format,
		success:function(data){
			if(data != null){
				$('#result-area').empty();
				$(data).find('film').each(function(){
				var id = $(this).find('id').text();	
				var title = $(this).find('title').text();
				var year = $(this).find('year').text();
				var director = $(this).find('director').text();
				var stars = $(this).find('stars').text();
				var review = $(this).find('review').text();
				$('#result-area').append("<li>Film ID: "+id+" <br> Title: "+title+" <br>Year: "+year+" <br>Director: "+director+" <br>Stars: "+stars+" <br>Review: "+review+"</li>");
			});
			}
		}
	})
}

function getFilmText() {
	var format = makeTitleString();
	
	jQuery.ajax({
		type:"GET",
		url:"show-film",
		data:format,
		success:function(data){
			$('#result-area').empty();
			$('#result-area').append(data);
		}
	})
}

//Update film implementation.
function updateFilm() {
	var data = makeFullString();
	jQuery.ajax({
		type:"POST",
		url:"update-film",
		data:data,
		success:function(){
			$("#result-update-modal").append("Film Updated")
		}
	})
}

//Insert film implementation
function insertFilm() {
	var data = makeInsertString();
	jQuery.ajax({
		type:"POST",
		url:"insert-film",
		data:data,
		success:function(){
			$("#result-insert-modal").append("Film Inserted")
		}
	})
}

//Delete film implementation
function deleteFilm() {
	var data = makeDeleteString();
	jQuery.ajax({
		type:"POST",
		url:"delete-film",
		data:data,
		success:function(){
			$("#result-delete-modal").append("Film Deleted")
		}
	})
}
