import React from "react";
import catsImage from "../assets/cat-gif.gif";

const Header = ({ search, setSearch, handleSearch }) => (
    <div className="relative bg-gradient-to-br from-gray-900 to-gray-800 text-white pb-24 pt-12 px-4 text-center shadow-lg">
        <div className="max-w-4xl mx-auto">
            <div className="text-4xl md:text-6xl font-extrabold mb-4 flex justify-center align-center drop-shadow-md">
                <img src={catsImage} alt="" className="w-20" />
                <span className="mt-4">Hell Cat's Meal Explorer</span>
            </div>
            <p className="text-lg opacity-90">Search. Cook. Enjoy.</p>

            <form
                onSubmit={handleSearch}
                className="absolute left-1/2 -bottom-8 transform -translate-x-1/2 w-11/12 max-w-2xl bg-[#1a1a1a] p-2 rounded-full shadow-xl flex items-center"
            >
                <input
                    className="flex-grow bg-transparent px-6 py-3 text-gray-300 placeholder-gray-500 text-lg outline-none"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    placeholder="What are you craving? (e.g. Pasta)"
                />
                <button
                    type="submit"
                    className="bg-red-600 hover:bg-red-700 text-white px-8 py-3 rounded-full font-bold transition"
                >
                    Search
                </button>
            </form>
        </div>
    </div>
);

export default Header;