const groupItems = document.querySelectorAll(".group-item");

groupItems.forEach((groupItem) => {
    groupItem.addEventListener("click", (e) => {
        e.preventDefault();

        const group_id = groupItem.querySelector("#group-id").value;
        window.location.href = "/api/groups/" + group_id + "/detail";
    });
});
