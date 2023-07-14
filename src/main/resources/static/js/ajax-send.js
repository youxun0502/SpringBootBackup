const url = "http://localhost:8081/my-app/customer/insert2";
const myBtn = document.querySelector('.my-customer-data-btn');
const output = document.querySelector('.output');

/////jQuery ajax Version/////
// myBtn.addEventListener('click', (e) => {
//     const name_input = document.getElementById('name_input').value;

//     const level_input = document.getElementById('level_input').value;

//     let dtoObject = {
//         customerName: name_input,
//         customerLevel: level_input
//     }

//     //js 物件轉換成 JSON 格式字串
//     const dtoJsonString = JSON.stringify(dtoObject);

//     $.ajax({
//         method: "post",
//         url: url,
//         contentType: 'application/json;charset=UTF-8',//送過去的格式
//         data: dtoJsonString,
//         success: function (response) {
//             name_input.value = '';
//             level_input.value = '';
//             console.log("jquery result " + JSON.stringify(response));
//         },
//         error: function (err) {
//             console.log('something wrong~~');
//             console.log('err' + err);
//         }
//     });
// })


///////// fetch() Version /////////
myBtn.addEventListener('click', (e) => {
    const name_input = document.getElementById('name_input').value;

    const level_input = document.getElementById('level_input').value;

    //js物件
    let dtoObject = {
        customerName: name_input,
        customerLevel: level_input
    }

    //js 物件轉換成 JSON 格式字串
    const dtoJsonString = JSON.stringify(dtoObject);

    fetch(url, {
        method: 'post',
        body: dtoJsonString,
        headers: { 'content-Type': 'application/json' }
    })
        .then(response => {
            name_input.value = '';
            level_input.value = '';
            return response.json();
        })
        .then(jsObject => {
            console.log('json data' + JSON.stringify(jsObject));
        })
        .catch(err => {
            console.log('err: ' + err);
        })
})