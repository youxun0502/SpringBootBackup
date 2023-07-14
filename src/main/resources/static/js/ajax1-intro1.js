///////////////v1 XMLHttpRequest 版本 ////////////////////////
// const firstRequest = new XMLHttpRequest();

// firstRequest.addEventListener('load', function () {
//     if (firstRequest.status !== 200) {
//         console.log('發送成功但回應不是200');
//         throw new Error("State code error" + firstRequest.status)
//     } else {
//         console.log('發送成功且回應200');
//         console.log('資料如下');
//         // console.log(this.responseText); //JSON字串
//         let data = JSON.parse(this.responseText);
//         console.log(data); //JSON字串轉換成JS物件
//     }
// })

// firstRequest.addEventListener('error', function () {
//     console.log('發送失敗!');
// })
// firstRequest.open('GET', 'http://localhost:8080/my-app/customers');
// firstRequest.send();
// console.log('request 已發送');


///////////////v1 jQuery Ajax 版本 ////////////////////////
// const url = 'http://localhost:8081/my-app/customerss';

// // const myBtn = document.querySelector(".my-customer-data-btn");

// const output = document.querySelector(".output");
// $('.my-customer-data-btn').click(function (event) {
//     $.ajax({
//         url: url,
//         method: 'Get',
//         success: function (result) {
//             //jquery會自動把JSON字串轉成JS物件(不需要 JSON.parse)
//             console.log('jquery result:' + result[0].name);
//             htmlMaker(result);

//         },
//         error: function (err) {
//             console.log('err:' + err);
//             errorHtmlMaker();
//         }
//     })
// })

function htmlMaker(data) {
    output.innerHTML += '<h5>回傳的 Data</h5>';
    data.forEach((el, index) => {
        const div = document.createElement('div');
        div.innerHTML += `<div> 會員編號: ${el.id}</div>`;
        div.innerHTML += `<div> 會員姓名: ${el.name}</div>`;
        div.innerHTML += `<div> 會員等級: ${el.level}</div>`;
        output.append(div);
    })
}

// function errorHtmlMaker() {
//     output.innerHTML = '<h2>發生不可預期的問題，請聯繫客服專線 0966-666-666</h2>'
// }


///////////////test Promise///////////////

// const willGetData = new Promise((resolve, reject) => {
//     const rand = Math.random();
//     console.log(rand);
//     if (rand < 0.5) {
//         resolve();
//     } else
//         reject();
// })

// willGetData.then(() => {
//     console.log('resolve狀態(than)');
//     console.log('小於0.5');
// })

// willGetData.catch(() => {
//     console.log('reject狀態(catch)');
//     console.log('大於0.5');
// })

//////////////v3 fetch api///////////////

// const url = 'http://localhost:8081/my-app/customers';

// const myBtn = document.querySelector(".my-customer-data-btn");

// const output = document.querySelector(".output");

// myBtn.addEventListener('click', (e) => {
//     fetch(url)
//         .then(response => {
//             return response.json()
//                 .then((jsObject) => {
//                     htmlMaker(jsObject)
//                 })
//                 .catch(err => {
//                     console.log('err:' + err);
//                 })
//         })
// })


////////////////// v4 axios ///////////////////

const url = 'http://localhost:8081/my-app/customers';
const myBtn = document.querySelector(".my-customer-data-btn");
const output = document.querySelector(".output");

myBtn.addEventListener('click', (e) => {
    axios.get(url)
        .then(res => {
            console.log(res.data);
            htmlMaker(res.data)
        })
        .catch(err => {
            console.log('errL: ' + err);
        })
})