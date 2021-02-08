<%@page import="logic.engineeringclasses.bean.managerestaurant.BeanReview"%>
<%@page import="logic.engineeringclasses.bean.managerestaurant.BeanListReviews"%>
<%@page import="logic.engineeringclasses.others.Session" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	Session s;
	s = (Session)session.getAttribute("session");
%>

    <%
	if(request.getParameter("home10")!=null) {
		%>
		<jsp:forward page="HomePageOwner.jsp"></jsp:forward>
		<%
	}
%>
 <%
	if(request.getParameter("continue10")!=null) {
		%>
		<jsp:forward page="HomePageOwner.jsp"></jsp:forward>
		<%
	}
%>

<%
	if(request.getParameter("manageMenu10")!=null) {
		%>
		<jsp:forward page="RestaurantMenuview.jsp"></jsp:forward>
		<%
	}
%>

<%
	if(request.getParameter("sponsorRestaurant10")!=null) {
		%>
		<jsp:forward page="CreatingRestaurantView.jsp"></jsp:forward>
		<%
	}
%>

    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Reviews</title>
</head>
<link rel="stylesheet" type="text/css" href="ReviewOwnerNotifications.css">

<body>

<div class="container">
		<form action="ReviewNotificationsView.jsp" method="get">
		
			<input id="home" type="submit" name="home10" value="Home">
			<input id="manageMenu" type="submit" name="manageMenu10" value="Manage Menu">
			<input id="sponsorRestaurant" type="submit" name="sponsorRestaurant10" value="Sponsor Restaurant">
			
			
			
			<img id="fotoUtente" alt="image" src="utente.jpg"/>
			
			<label id="n"><%out.print(s.getUser().getUsername()); %></label>
			
<%BeanListReviews beanListReviews = (BeanListReviews)session.getAttribute("beanReviews"); %>
			
			
			<table >
			<caption></caption>
				<tr>
					<th id="turistath">Turista</th>
					<th id="ristoranteth">Ristorante</th>
					<th id="recensioneth">Leggi recensione</th>
					<th id="vototh">Voto</th>
				</tr>
<%
			BeanReview beanReview;
			for(int i=0;i<beanListReviews.getRestaurants().size();i++){
				beanReview = new BeanReview(beanListReviews.getTourists().get(i), beanListReviews.getRestaurants().get(i), beanListReviews.getContents().get(i), beanListReviews.getVotes().get(i));
				System.out.print(beanListReviews.getTourists().get(i) +beanListReviews.getRestaurants().get(i)+ beanListReviews.getContents().get(i)+ beanListReviews.getVotes().get(i));
				%>
				<tr>
					<th id="riga1"><%=beanReview.getTourist() %></th>
					<th id="riga2"><%=beanReview.getRestaurant() %></th>
					<th id="riga3"><%=beanReview.getContent() %></th>
					<th id="riga4"><%=beanReview.getVoto() %></th>
				</tr>
				<%
			}
%>
				
			</table>
			
			
		

			
			<input id="continue" name="continue10" value="CONTINUE" type="submit">
			
			
			
			
			
		</form>
	</div>
</body>
</html>
