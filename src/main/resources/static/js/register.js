document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this); // Obtiene los datos del formulario

    // Realiza una solicitud POST al backend
    fetch('http://localhost:8080/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Object.fromEntries(formData)) // Convierte FormData a un objeto y luego a JSON
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al registrar usuario');
        }
        return response.json();
    })
    .then(data => {
        // Maneja la respuesta del backend
        alert(`Usuario registrado: ${data.username}`);
        window.location.href = "/acceder";
        // Puedes redirigir a otra página o mostrar un mensaje de éxito
    })
    .catch(error => {
        console.error('Error:', error);
        // Puedes mostrar un mensaje de error al usuario
    });
});