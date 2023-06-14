const addMemberBtn = document.querySelector("#add-member-btn");
const groupDelBtn = document.querySelector(".group-del-btn");
const settleBtn = document.querySelector(".clear-btn");

// addMemberBtn.addEventListener("click", (e) => {
//     e.preventDefault();
//
//     const group_id = document.querySelector("#group-id").value;
//     window.location.href = `/api/groups/${group_id}/members/add`;
// });

groupDelBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const group_id = document.querySelector("#group-id").value;

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `/api/groups/${group_id}/delete`);
    xhr.setRequestHeader("Content-Type", "application/json");
    let flag = false;
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            if (flag)
                window.location.href = `/`;
        } else {
            flag = true
            console.log("status : ", xhr.status);
            // 오류 처리
            // console.log(xhr.responseText);
            const errorResponse = JSON.parse(xhr.responseText);
            console.log("errorResponse : ", errorResponse)
            console.log("에러 발생:", errorResponse.error);
            alert(errorResponse.error)
        }
    }
    xhr.send();
});

settleBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const group_id = document.querySelector("#group-id").value;

    if (!confirm("정말 정산하시겠습니까?")) {
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open("POST", `/api/groups/${group_id}/settle`);
    xhr.setRequestHeader("Content-Type", "application/json");
    let flag = false;
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 응답 데이터 처리
            if (flag)
                window.location.href = `/api/groups/${group_id}/detail`;
        } else {
            flag = true
            console.log("status : ", xhr.status);
            // 오류 처리
            // console.log(xhr.responseText);
            const errorResponse = JSON.parse(xhr.responseText);
            console.log("errorResponse : ", errorResponse)
            console.log("에러 발생:", errorResponse.error);
            alert(errorResponse.error)
        }
    }
    xhr.send();
});