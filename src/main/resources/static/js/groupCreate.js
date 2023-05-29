const userSearchBar = document.getElementById("user-search-bar");
const groupCreateForm = document.getElementById("group-create-form");

const memberList = [];

const getInputValue = () => {
    userSearchBar.addEventListener("keypress", (e) => {
        if (e.key === "Enter") {
            searchUser(e.target.value);
        }
    })
}

// 사용자 검색 요청을 처리하는 함수
const searchUser = (keyword) => {
    // AJAX 요청
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/users/search?nickname=" + keyword, true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const user = JSON.parse(xhr.responseText);
            console.log(user);
            addMember(user);
        } else {
            // 오류 처리
            console.error(xhr.responseText);
        }
    };
    xhr.send();
}

const addMember = (user) => {
    // 중복 검사
    if (memberList.includes(user)) {
        alert("이미 추가된 사용자입니다.");
        return;
    }
    memberList.push(user);

    addUserToTable(user);
}

// 테이블에 사용자 추가하는 함수
const addUserToTable = (user) => {
    console.log("addUserToTable", user.username);
    const table = document.querySelector(".member-list");
    // <tr> 태그 추가
    const newRow = table.insertRow();
    newRow.classList.add("member-item");
    newRow.classList.add("add-member");

    // <input type="checkbox" checked="checked">
    const checkboxCell = newRow.insertCell();
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.checked = true; // Set the checkbox state as needed
    checkboxCell.appendChild(checkbox);

    // <img src="..." alt="Profile Image">
    const profileCell = newRow.insertCell();
    const profileImg = document.createElement("img");
    // profileImg.src = user.profileImageUrl; // Set the profile image URL
    profileImg.src = "/icon/ic_boy2.png"
    profileImg.alt = "Profile Image";
    profileCell.appendChild(profileImg);
    profileCell.classList.add("profile");

    // <span class="name">...</span>
    const nameCell = newRow.insertCell();
    nameCell.textContent = user.username;
    nameCell.classList.add("name");

    // <span class="nickname">...</span>
    const nicknameCell = newRow.insertCell();
    nicknameCell.textContent = user.nickname;
    nicknameCell.classList.add("nickname");
}

groupCreateForm.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        e.preventDefault();
    }
});

groupCreateForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const groupName = document.querySelector(".group-name-input").value;
    const memberData = memberList.map((user) => (
            {
                user_id: user.user_id,
                username: user.username,
                nickname: user.nickname,
            }
        ));
    const requestBody = {
        group_name: groupName,
        members: memberData,
    };

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/groups/save", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const group_id = JSON.parse(xhr.responseText);
            console.log(group_id);
            alert("그룹 생성에 성공했습니다.");
            window.location.href = "/api/group/" + group_id + "/detail";
        } else {
            // 오류 처리
            console.error(xhr.responseText);
            alert("그룹 생성에 실패했습니다.");
        }
    }
    xhr.send(JSON.stringify(requestBody));
});


getInputValue();
