const ReceiptAddBtn = document.querySelector(".receipt-add-btn");
const MeetingDeleteBtn = document.querySelector(".meeting-del-btn");
const ReceiptsItems = document.querySelectorAll(".receipt-item");

const group_id = document.querySelector("#group-id").value;
const meeting_id = document.querySelector("#meeting-id").value;

ReceiptAddBtn.addEventListener("click", (e) => {
    e.preventDefault();

    window.location.href = `/api/groups/${group_id}/meetings/${meeting_id}/receipts/create`;
});

MeetingDeleteBtn.addEventListener("click", (e) => {
    e.preventDefault();

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `/api/groups/${group_id}/meetings/${meeting_id}/delete`);
    xhr.setRequestHeader("Content-Type", "application/json");
    let flag = false;
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            if (flag) window.location.href = `/groups/${group_id}/detail`;
        } else {
            flag = true;
            console.log("status : ", xhr.status);
            // 오류 처리
            // console.log(xhr.responseText);
            flag = true;
            console.log("status : ", xhr.status);
            // 오류 처리
            const errorResponse = JSON.parse(xhr.responseText);
            console.log("errorResponse : ", errorResponse)
            console.log("에러 발생:", errorResponse.error);
            alert(errorResponse.error)
        }
    }
    xhr.send();
});

ReceiptsItems.forEach((item) => {
    item.addEventListener("click", (e) => {
        e.preventDefault();

        const receipt_id = item.querySelector("#receipt-id").value;
        window.location.href = `/api/groups/${group_id}/meetings/${meeting_id}/receipts/${receipt_id}/detail`;
    });
});

