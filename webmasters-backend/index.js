const express = require('express');
const { commerce, image } = require('faker');
const { v4: uuidv4 } = require('uuid');

const app = express();

// Generate some random product data.
const products = Array(100)
  .fill({})
  .map(() => {
    return {
      id: uuidv4(),
      name: commerce.productName(),
      description: commerce.productDescription(),
      category: commerce.department(),
      price: commerce.price(),
      imageUrl: image.technics(200, 200),
    };
  });

// Serve random product data.
app.get('/products', (req, res) => {
  res.json(products);
});

// Listen on some port.
app.listen(3000, () => {
  console.log('Listening on port 3000');
});
