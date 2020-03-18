function alerta_error_server() {
    eval(
        "    Swal.fire(\n" +
        "        'Server error',\n" +
        "        'Try again later!',\n" +
        "        'info'\n" +
        "    )"
    );
}

function alerta_error_user() {
    eval(
        "    Swal.fire(\n" +
        "        'Invalid account',\n" +
        "        'Username or password are incorrect',\n" +
        "        'error'\n" +
        "    )"
    );
}

function save_session_data(key, value) {
    console.log(key, value);
    sessionStorage.setItem(key, value);
}