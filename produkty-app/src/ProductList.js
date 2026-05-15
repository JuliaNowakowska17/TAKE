import ProductItem from "./ProductItem";
import { useState } from 'react';

function ProductList( { products } ) {
    const [filter, setFilter] = useState("");

    function handleFilterChange(event) {
        setFilter(event.target.value);
    }

    return (
        <div>
            <h1>List of Products</h1>
            <label> Filter by product title: 
                <input type="text" value={filter} onChange={handleFilterChange} />
            </label>
            <ul>
                {products
                .filter(product => product.title.toLowerCase().includes(filter.toLowerCase()))
                .map(product => (
                    <ProductItem
                        key={product.id}
                        id={product.id}
                        title={product.title}
                        brand={product.brand}
                    />
                ))}
            </ul>
        </div>
    )
}

export default ProductList;