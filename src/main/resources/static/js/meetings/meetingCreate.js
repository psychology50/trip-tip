const meetingAddBtn = document.querySelector(".meeting-add-btn");

meetingAddBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const group_id = document.querySelector("#group-id").value;

    if(is_duplicated()) {
        alert("이미 추가된 모임입니다.");
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/groups/" + group_id + "/meetings/save", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            addMeeting(response)
        } else {
            // 오류 처리
            console.log(xhr.responseText);
        }
    }
    xhr.send();
});

const is_duplicated = () => {
    const today = new Date();
    const formattedToday = today.toISOString().split("T")[0];
    console.log("오늘의 날짜는 ", formattedToday, "입니다.");

    let flag = false;
    const currentMeetings = document.querySelectorAll("#meeting-day");
    currentMeetings.forEach((currentMeeting) => {
        console.log(currentMeeting.innerHTML)
        if(currentMeeting.innerHTML === formattedToday) {
            flag = true;
        }
    });

    return flag;
}

const addMeeting = ({meeting_id, meeting_day}) => {
    console.log("addMeeting() 호출 ", meeting_id, meeting_day);
    const meetingList = document.querySelector(".meeting-list");

    const meetingItem = document.createElement("article");
    meetingItem.classList.add("meeting-item");
    meetingItem.classList.add("flex");

    const meetingId = document.createElement("input");
    meetingId.type = "hidden";
    meetingId.id = "meeting-id";
    meetingId.value = meeting_id;

    const meetingDate = document.createElement("p");
    meetingDate.innerText = meeting_day;
    meetingDate.id = "meeting-day";

    meetingItem.appendChild(meetingId);
    meetingItem.appendChild(meetingDate);
    meetingList.appendChild(meetingItem);

    moveMeetingDetail();
}

const moveMeetingDetail = () => {
    const meetingItems = document.querySelectorAll(".meeting-item");

    meetingItems.forEach((meetingItem) => {
        meetingItem.addEventListener("click", (e) => {
            e.preventDefault();

            const group_id = document.querySelector("#group-id").value;
            const meeting_id = meetingItem.querySelector("#meeting-id").value;

            window.location.href = "/api/groups/" + group_id + "/meetings/" + meeting_id + "/detail";
        });
    });
}

moveMeetingDetail();