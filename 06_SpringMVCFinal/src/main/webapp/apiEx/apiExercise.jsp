<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>api 설정</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=m2wk1anmrr&submodules=geocoder"></script>
</head>
<body>
	<h2>1. 다음(카카오) 주소 찾기 api</h2>
	<input type="text" id="sample4_postcode" placeholder="우편번호">
	<input type="button" onclick="sample4_execDaumPostcode()"
		value="우편번호 찾기">
	<br>
	<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
	<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
	<span id="guide" style="color: #999; display: none"></span>
	<input type="text" id="sample4_detailAddress" placeholder="상세주소">
	<input type="text" id="sample4_extraAddress" placeholder="참고항목">



	<h2>2. 네이버 지도</h2>

	<div id="map" style="width: 100%; height: 400px;"></div>

	<script>
		window.onload = function() {
			/* var mapOptions = {
			    center: new naver.maps.LatLng(37.3595704, 127.105399),
			    zoom: 10
			}; */

			var map = new naver.maps.Map('map', {
				center : new naver.maps.LatLng(37.56793029975566,
						126.9830284252086),
				zoom : 17,
				zoomControl : true,
				zoomControlOption : {
					position : naver.maps.Position.TOP_RIGHT,
					style : naver.maps.ZoomControlStyle.SMALL
				}
			});
			//마커객체생성
			var marker = new naver.maps.Marker({
				position : new naver.maps.LatLng(37.56793029975566,
						126.9830284252086),
				map : map
			});
			//마커뜨게할때
			var infoWindow = new naver.maps.InfoWindow();
			var contentString = [ "<div class='iw_inner'>"," <h3>KH정보교육원</h3>"," <p>서울시 종로구 남대문로 120 대일빌딩 2,3층</p>","</div>"].join("");
			naver.maps.Event.addListener(marker, "click", function(e) {
				if (infoWindow.getMap()) {
					infoWindow.close();
				} else {
					infoWindow = new naver.maps.InfoWindow({
						content : contentString
					});
					infoWindow.open(map, marker);
				}
			});
			
			naver.maps.Event.addListener(map,"click",function(e){
				marker.setPosition(e.coord);
				if(infoWindow != null){
					if(infoWindow.getMap()){
						infoWindow.close();
					}
				}
				naver.maps.Service.reverseGeocode({
					location : new naver.maps.LatLng(e.coord.lat(), e.coord.lng()),
				}, function(status, response){
					if(status !== naver.maps.Service.Status.OK){
						return alert("정보가 없습니다");
					}
					var result = response.result;
					items = result.items;
					address = items[2].address;
					contentString = [ "<div class='iw_inner'>", "<p>"+address+"</p>", "</div>"].join("");
				});
			});
		};

		//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		function sample4_execDaumPostcode() {
			new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== ''
								&& /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== ''
								&& data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
		}
	</script>
</body>
</html>