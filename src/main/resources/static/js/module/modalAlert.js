const modalAlert = (message) => {
    const modal = document.createElement("div");
    modal.id = "modal";

    const modalContent = document.createElement("div");
    modalContent.className = "modal-content";

    const modalMessage = document.createElement("p");
    modalMessage.textContent = message;

    const modalBtnY = document.createElement("button");
    modalBtnY.className = "modal-btn-y";
    modalBtnY.textContent = "확인";
    modalBtnY.addEventListener("click", () => {
        modal.remove();
        return true;
    });

    const modalBtnN = document.createElement("button");
    modalBtnN.className = "modal-btn-n";
    modalBtnN.textContent = "취소";
    modalBtnN.addEventListener("click", () => {
        modal.remove();
        return false;
    });

    modalContent.appendChild(modalMessage);
    modalContent.appendChild(modalBtnY);
    modalContent.appendChild(modalBtnN);
    modal.appendChild(modalContent);
    document.body.appendChild(modal);
}

export default modalAlert;