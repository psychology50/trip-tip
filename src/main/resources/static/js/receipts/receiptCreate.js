const group_id = document.querySelector("#group-id").value;
const meeting_id = document.querySelector("#meeting-id").value;

const dutchPayBtn = document.querySelector("#dutch-pay-btn");
const eachPayBtn = document.querySelector("#each-pay-btn");

const form = document.querySelector("#receipt-create-form");

let flag = true; // true: dutchPayBtn, false: equalPayBtn

const initBtnEvent = (response) => {
    dutchPayBtn.addEventListener("click", (e) => {
        e.preventDefault();

        if (!flag) {
            flag = true;
            dutchPayBtn.classList.add("active");
            eachPayBtn.classList.remove("active");
            paintPage(response);
        }
    });
    eachPayBtn.addEventListener("click", (e) => {
        e.preventDefault();

        if (flag) {
            flag = false;
            dutchPayBtn.classList.remove("active");
            eachPayBtn.classList.add("active");
            paintPage(response);
        }
    });
}


const initPage = () => {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `/api/groups/${group_id}/meetings/${meeting_id}/receipts/create-form`);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            paintPage(response);
            initBtnEvent(response);
            addFormEventListener(response);
        } else {
            console.log("status : ", xhr.status);
            // 오류 처리
            console.log(xhr.responseText);
        }
    }
    xhr.send();
}

const paintPage = (response) => {
    paintProfile(response);
    paintReceipts(response);
};

const paintProfile = (response) => {
    // const groupImage = document.querySelector(".group-image");
    // groupImage.src = response.group_image;

    const groupCode = document.querySelector(".group-code");
    groupCode.textContent = response.meeting.group.group_code;

    const groupName = document.querySelector(".group-name");
    groupName.textContent = response.meeting.group.group_name;

    const leaderMeeting = document.querySelector(".leader-meeting");
    leaderMeeting.textContent = response.meeting?.group?.leader?.username;

    const meetingDate = document.querySelector(".meeting-date");
    meetingDate.textContent = response.meeting?.meeting_day;
};

const paintReceipts = (response) => {
    form.innerHTML = "";

    if (flag) {
        const totalInput = document.createElement("input");
        totalInput.type = "number";
        totalInput.name = "total";
        totalInput.className = "total";
        totalInput.placeholder = "금액을 입력해주세요"
        totalInput.required = true;
        form.appendChild(totalInput);
    }

    const table = document.createElement("table");
    table.classList.add("participant-list")
    form.appendChild(table);

    table.appendChild(PayerTableRow(response.payer));
    response.participationUsers.forEach((user) => {
        if (user.user_id !== response.payer.user_id) {
            const userRow = document.createElement("tr");
            userRow.classList.add("participant");
            userRow.classList.add("add-member");

            const checkbox = document.createElement("td");
            const userCheckbox = document.createElement("input");
            userCheckbox.type = "checkbox";
            userCheckbox.name = "participationUsers";
            userCheckbox.value = user.user_id;
            userCheckbox.checked = true;
            checkbox.appendChild(userCheckbox);
            userRow.appendChild(checkbox);

            const userProfile = document.createElement("td");
            userProfile.classList.add("profile");

            const userProfileImage = document.createElement("img");
            userProfileImage.src = "/icon/ic_boy1.png";
            userProfileImage.alt = "default profile img";
            userProfile.appendChild(userProfileImage);
            userRow.appendChild(userProfile);

            const userName = document.createElement("td");
            userName.className = "name"
            userName.textContent = user.username;
            userRow.appendChild(userName);

            userRow.appendChild(
                (flag) ? UserNickname(user.nickname) : EachInput(user.user_id)
            );

            table.appendChild(userRow);
        }
    });

    form.appendChild(paintSubmitBtn());
}

const PayerTableRow = (payer) => {
    const payerRow = document.createElement("tr");
    payerRow.id = "payer";
    payerRow.classList.add("participant");

    const checkbox = document.createElement("td");
    const payerCheckbox = document.createElement("input");
    payerCheckbox.type = "checkbox";
    payerCheckbox.name = "participationUsers";
    payerCheckbox.value = payer.user_id;
    payerCheckbox.checked = true;
    checkbox.appendChild(payerCheckbox);
    payerRow.appendChild(checkbox);

    const payerProfile = document.createElement("td");
    const payerProfileImage = document.createElement("img");
    payerProfileImage.src = "/icon/ic_boy1.png";
    payerProfileImage.alt = "default profile img";
    payerProfile.appendChild(payerProfileImage);
    payerRow.appendChild(payerProfile);

    const payerName = document.createElement("td");
    payerName.textContent = payer.username;
    payerRow.appendChild(payerName);

    payerRow.appendChild(
        (flag) ? UserNickname(payer.nickname) : EachInput(payer.user_id)
    );

    return payerRow;
}

const UserNickname = (nickname) => {
    const userNickname = document.createElement("td");
    userNickname.className = "nickname"
    userNickname.textContent = nickname;
    return userNickname;
}

const EachInput = (id) => {
    const userEach = document.createElement("td");
    userEach.className = "nickname";

    const userEachInput = document.createElement("input");
    userEachInput.type = "number";
    userEachInput.name = `cost_${id}`;
    userEachInput.className = "each-input";
    userEachInput.placeholder = '0';
    userEachInput.required = true;
    userEach.appendChild(userEachInput);
    return userEach;
}

const paintSubmitBtn = () => {
    const btnWrapper = document.createElement("div");
    btnWrapper.classList.add("flex");
    btnWrapper.style.justifyContent = "end";

    const submitButton = document.createElement("button");
    submitButton.type = "submit";
    submitButton.classList.add("receipt-submit-btn");
    submitButton.classList.add("bold")
    submitButton.textContent = "생성";
    btnWrapper.appendChild(submitButton);

    return btnWrapper
}

const addFormEventListener = (response) => {
    form.addEventListener("submit", (e) => pushData(e, response));
}

const pushData = (e, response) => {
    e.preventDefault();

    const formData = new FormData(form);
    const selectedUsers = formData.getAll("participationUsers");
    const N = selectedUsers.length;
    // console.log("formData = ", formData.forEach((value, key) => console.log(key, value)));
    const data = {
        "receipt_id": null,
        "receipt_name": "새 영수증",
        "total": (formData.get("total") != null) ? formData.get("total") : 0,
        "is_clear": false,
        "payer": response.payer,
        "participationUsers": response.participationUsers.map((user) => {
            const is_checked = selectedUsers.includes(user.user_id.toString());
            return (is_checked)
                ? {...user,
                    cost: (formData.get("total") != null)
                        ? formData.get("total")/N
                        : formData.get(`cost_${user.user_id}`),
                    selected: (formData.get(`cost_${user.user_id}`) !== '0')}
                : {...user, selected: false, cost: 0};
        }),
        "meeting": response.meeting,
    };

    console.log("data = ", data);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", `/api/groups/${group_id}/meetings/${meeting_id}/receipts/create`);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            alert("영수증이 생성되었습니다.");
            window.location.href = `/api/groups/${group_id}/meetings/${meeting_id}/detail`;
        } else {
            console.log("status : ", xhr.status);
            // 오류 처리
            console.log(xhr.responseText);
        }
    }
    xhr.send(JSON.stringify(data));
}

initPage();