const ReceiptAddBtn = document.querySelector(".receipt-add-btn");
const MeetingDeleteBtn = document.querySelector(".meeting-del-btn");

ReceiptAddBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const group_id = document.querySelector("#group-id").value;
    const meeting_id = document.querySelector("#meeting-id").value;

    window.location.href = `/api/groups/${group_id}/meetings/${meeting_id}/receipts/create`;
});

MeetingDeleteBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const group_id = document.querySelector("#group-id").value;
    const meeting_id = document.querySelector("#meeting-id").value;

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `/api/groups/${group_id}/meetings/${meeting_id}/delete`);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            const response = JSON.parse(xhr.responseText);
            console.log(response);
            window.location.href = `/groups/${group_id}/detail`;
        } else {
            console.log("status : ", xhr.status);
            // 오류 처리
            console.log(xhr.responseText);
        }
    }
    xhr.send();
});