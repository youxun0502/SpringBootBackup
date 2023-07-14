let editBtn = document.getElementsByClassName('edit-btn');

for (i = 0; i < editBtn.length; i++) {
	var editListen = editBtn[i].addEventListener('click', function (e) {
		let msgID = this.getAttribute('data-msgid');
		console.log('msgID: ' + msgID);
		editMsg(msgID)
	})
}


function insertAfter(referenceNode, newNode) {
	referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling)
	//referenceNode.nextSiblig 為<span> 的下一個Node, 我們要在下一個Node前面加東西
}

function editMsg(msgID) {
	let content = document.querySelector(`[data-contentid='${msgID}']`)

	let cardBody = content.parentNode;
	if (cardBody.childNodes.length <= 5) {



		const saveAndUpdateButton = document.createElement('button');

		saveAndUpdateButton.className = 'save-update_btn btn btn-primary btn-sm';
		saveAndUpdateButton.innerHTML = '修改並送出';
		insertAfter(content, saveAndUpdateButton)

		content.setAttribute("contenteditable", "true");
		content.focus();

		saveAndUpdateButton.addEventListener('click', (e) => {
			const putObject = {
				"id": msgID,
				"newText": content.textContent
			}
			content.setAttribute("contenteditable", "false");
			saveAndUpdateButton.remove()

			makeRequest(putObject)
		}), { once: true }
	}
}


function makeRequest(json) {
	axios.put("http://localhost:8081/my-app/messages/api/updateOne", json)
		.then(res => {
			console.log('res ' + res);
		})
		.catch(err => {
			console.log('err ' + err);
		})
}

/////////////////ajax 刪除，回傳頁面////////////

const deleteBtn = document.getElementsByClassName('delete-btn');

for (i = 0; i < deleteBtn.length; i++) {
	deleteBtn[i].addEventListener('click', function (e) {
		let msgID = this.getAttribute('data-msgid');
		deleteAjaxReq(msgID)
	})
}


function deleteAjaxReq(deleteId) {
	axios({
		method: 'delete',
		url: 'http://localhost:8081/my-app/messages/delete',
		params: {
			id: deleteId
		},
		responseType: 'document',//預設是JSON
		headers: { 'Content-Type': 'application/x-www-form-urlencode' }
		//get以外的請求 axios 會把 header 轉成 application/json, 或其他自動判斷格式
		//所以不是送JSON, 要調整Content-Type為urlencode
	})
		.then(res => {
			console.log('res ' + res);
			window.location.href = "http://localhost:8081/my-app/messages/mix";
		})
		.catch(err => {
			console.log('err' + err);
		})
}