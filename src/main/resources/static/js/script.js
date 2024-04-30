const $headerUser = document.querySelector("#header-username");


document.addEventListener("DOMContentLoaded", () => {
    const $formulario = document.querySelector("#formulario")
    const $inputTarea = document.querySelector("#input-tarea")

    $formulario.addEventListener("submit", function(e){
        e.preventDefault();
        const taskNameInput = String($inputTarea.value).trim()
        if (taskNameInput.length === 0){
            Swal.fire({
                titleText: "Precaución",
                text: "No debe ingresar espacios en blanco",
                icon: "error",
                confirmButtonObject: "Ok!",
            })
        }else{
            addTask();
        }
    })
    //POST A TASK
    function addTask(){
        fetch('http://localhost:8080/users/me', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                    description: $inputTarea.value
            })})
            .then(response => {
                if (response.ok) {
                    console.log('Tarea guardada exitosamente');
                    getAllTasks()
                } else {
                    console.error('Error al guardar la tarea:', response.statusText);
                }
            })
            .catch(error => {
                console.error('Error de red:', error);
            })
    }
})
//GET ALL TASK
function getAllTasks(){
    fetch(`http://localhost:8080/users/me/tasks`)
      .then(response => {
        if (response.status === 204) {
          return []
        }else{
            return response.json();
        }
      })
      .then(tasks => {
          console.log(tasks);
          if (tasks.length > 0){
              let listaTareasHtml = '';
              let index = 1;
              tasks.forEach(task => {
                  let taskHtml = `<tr>
                                      <td>${index}</td>
                                      <td id="tarea" class="${task.complete == true ? 'tachar' : ''}">${task.description}</td>
                                      <td id = "completado" hidden>${task.complete}</td>
                                      <td id = "tarea-id" hidden>${task.id}</td>
                                      <td><a id = "tachar" onclick="tacharTarea(${task.id}, '${task.complete}', event)"
                                      class="btn btn-sm btn-block btn-info">Tachar</a></td>
                                      <td><a href="#" onclick="borrarTarea('${task.description}', ${task.id}, event)"
                                      class="btn btn-sm btn-block btn-danger">Eliminar</a></td>
                                  </tr>`;
                  listaTareasHtml += taskHtml;
                  index++;
              });
              document.querySelector('table tbody').outerHTML = listaTareasHtml;
          }else{
            document.querySelector('table tbody').outerHTML = `<tr>
                                                                 <td></td>
                                                                 <td>La lista de tareas se encuentra vacía!!</td>
                                                               </tr>`;
          }
      })
      .catch(error => {
        console.error('Hubo un problema con la operación de fetch:', error);
      });
}

//DELETE TASK
function deleteTask(taskId){
    fetch(`http://localhost:8080/tasks/${taskId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }})
     .then(response => {
         if (response.ok) {
             console.log('Tarea borrada exitosamente');
             location.reload()
         } else {
             console.error('Error al borrar la tarea:', response.statusText);
         }
     })
     .catch(error => {
         console.error('Error de red:', error);
     })
}
function borrarTarea(description, taskId, e){
    e.preventDefault()
    Swal.fire({
        title: `¿Está seguro que desea eliminar el curso\n ${description}?`,
        showCancelButton: true,
        confirmButtonText: "Eliminar",
        confirmButtonColor: "crimson",
        backdrop: true,
        showLoaderOnConfirm: true,
        preConfirm: ()=>{
            deleteTask(taskId);
            location.reload()
        },
        allowOutsideClick: ()=>false,
        allowEscapeKey: ()=>false
    })
}
function putTask(bool, taskId){
    fetch(`http://localhost:8080/tasks/${taskId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            complete: bool
        })
    })
    .then(response => {
             if (response.ok) {
                 console.log('Tarea actualizada exitosamente');
             } else {
                 console.error('Error al actualizar la tarea:', response.statusText);
             }
         })
         .catch(error => {
             console.error('Error de red:', error);
         })
}
function tacharTarea(taskId, complete, e){
    const tarea = e.target.closest('tr').querySelector("#tarea");
    if (!tarea.classList.contains("tachar")){
        putTask(true, taskId)
        tarea.classList.add("tachar")
    }else{
        putTask(false, taskId)
        tarea.classList.remove("tachar")
    }
}
getAllTasks()

