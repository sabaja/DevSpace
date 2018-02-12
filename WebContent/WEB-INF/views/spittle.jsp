<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<div class="spittleView">
	<form method="POST" action="exception">
		<div class="spittleId">
			<c:out value="id:${spittle.id} " />
		</div>
		<div class="spittleMessage">
			<c:out value="${spittle.message}" />
		</div>
		<div>
			<span class="spittleTime"><c:out value="${spittle.time}" /></span>
		</div>
		<div>
			<button onclick="goBack()">Back</button>

			<script>
				function goBack() {
					window.history.back();
				}
			</script>
			<input type="submit" value="Launch Exception" />

		</div>
	</form>
</div>