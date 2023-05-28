// 사용자 검색 요청을 처리하는 함수
function searchUser() {
    // 검색어 가져오기
    let searchKeyword = document.getElementById("searchInput").value;

    // AJAX 요청
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/users/search?keyword=" + searchKeyword, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 응답 데이터 처리
            const user = JSON.parse(xhr.responseText);
            addUserToTable(user);
        }
    };
    xhr.send();
}

// 테이블에 사용자 추가하는 함수
function addUserToTable(user) {
    const table = document.getElementById("member-list");
    const newRow = table.insertRow();
    newRow.classList.add("member-item");
    newRow.classList.add("add-member");

    const checkboxCell = newRow.insertCell();
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.checked = true; // Set the checkbox state as needed
    checkboxCell.appendChild(checkbox);

    const profileCell = newRow.insertCell();
    const profileImg = document.createElement("img");
    // profileImg.src = user.profileImageUrl; // Set the profile image URL
    profileImg.src = "/icon/ic_boy2.png"
    profileImg.alt = "Profile Image";
    profileCell.appendChild(profileImg);
    profileCell.classList.add("profile");

    const nameCell = newRow.insertCell();
    nameCell.textContent = user.username;
    nameCell.classList.add("name");

    const nicknameCell = newRow.insertCell();
    nicknameCell.textContent = user.nickname;
    nicknameCell.classList.add("nickname");
}
