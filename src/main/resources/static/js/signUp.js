const password = document.querySelector('#pw');
const passwordCheck = document.querySelector('#pw-chk');
const signUpBtn = document.querySelector('.register-btn');

let is_valid = false;

passwordCheck.addEventListener('keyup', () => {
    if (password.value === passwordCheck.value) {
        passwordCheck.setCustomValidity('');
        is_valid = true;
    } else {
        passwordCheck.setCustomValidity('비밀번호가 일치하지 않습니다.');
        is_valid = false;
    }
});

signUpBtn.addEventListener('click', () => {
    if (is_valid) {
        signUpBtn.form.submit();
    } else {
        alert('비밀번호가 일치하지 않습니다.');
        password.focus();
    }
});

