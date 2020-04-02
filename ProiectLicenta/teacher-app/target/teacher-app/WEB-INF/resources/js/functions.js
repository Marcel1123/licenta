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

function create_error_message(title, detail) {
    eval(
        "Swal.fire(\n'" + title + "',\n'" + detail + "'\n, 'error')"
    );
}

function create_success_message(title, detail) {
    eval(
        "Swal.fire(\n'" + title + "',\n'" + detail + "'\n, 'success')"
    );
}

function create_warning_message(title, detail) {
    eval(
        "Swal.fire(\n'" + title + "',\n'" + detail + "'\n, 'warning')"
    );
}

function save_session_data(key, value) {
    sessionStorage.setItem(key, value);
    localStorage.setItem(key, value);
    sessionStorage.setItem("it", 1);
}