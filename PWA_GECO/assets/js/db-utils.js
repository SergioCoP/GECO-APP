const gecoOfflineDB = new PouchDB('geco-offline');

const doOfflineRequestsSync = () => {
    const requests = [];
    return gecoOfflineDB.allDocs({include_docs: true}).then(async docs => {
        const {rows} = docs;
        rows.forEach(async row => {
            console.log(row);
            let response = await fetch(row.doc.u, {
                method: 'PUT',
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json",
                    "Authorization": `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyMDIwM3RuMDY2QHV0ZXouZWR1Lm14IiwiZXhwIjo0NDQ3OTE0OTAxMDgxMTYzLCJ1c2VyIjoiMjAyMDN0bjA2NkB1dGV6LmVkdS5teCJ9.E__s_TvtMtUk6X-DBTa7ea8OSuy7rsSbqEeKdZqbah4_lrPwt7bIUFBHpxcwivUm`
                },
                body: row.doc.b
            });
            const data = await response.json();
            if(data['changed']) {
                requests.push(gecoOfflineDB.remove(row.doc));
            }
        });
    });
}

const saveOfflineRequest = (body, url) => {
    let doc = {
        _id: new Date().toISOString(),
        b: body,
        u: url
    }
    return save = gecoOfflineDB.put(doc).then(() => {
        self.registration.sync.register('geco-offline');
        const response = {
            registered: true,
            offline: true
        }
        return new Response(JSON.stringify(response));
    }).catch((err) => {
        console.log(err);
        const response = {
            registered: false,
            offline: true
        }
        return new Response(JSON.stringify(response));
    })
}