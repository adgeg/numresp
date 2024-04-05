const express = require('express')
const {LOREM} = require("./strings");
const app = express()
const port = 3000


app.get('/tasks', (req, res) => {
  res.json([
    { id: '1', content: LOREM, status:'TO_DO'},
    { id: '2', content: LOREM, status:'DOING'},
    { id: '3', content: LOREM, status:'DONE'}
  ])
})


app.listen(port, () => { console.log(`Simple tasks API with node.js and Express`) })