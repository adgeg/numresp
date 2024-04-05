const express = require('express')
const compression = require('compression')
const {LOREM} = require("./strings")
const app = express()
const port = 3000

app.use(compression())

app.get('/tasks', (req, res) => {
  res.json([
    { id: '1', content: LOREM, status:'TO_DO'},
    { id: '2', content: LOREM, status:'DOING'},
    { id: '3', content: LOREM, status:'DONE'}
  ])
})

app.get('/tasks-cached', function (req, res, next) {
  res.header('Cache-control', `max-age=20`)
  next();
}, function (req, res, next) {
  console.log('REQUEST url', req.url)
  console.log('REQUEST headers', req.headers)


  res.json([
    { id: '1', content: LOREM, status:'TO_DO'},
    { id: '2', content: LOREM, status:'DOING'},
    { id: '3', content: LOREM, status:'DONE'},
    { id: '4', content: LOREM, status:'TO_DO'}
  ])
})


app.listen(port, () => { console.log(`Simple tasks API with node.js and Express`) })