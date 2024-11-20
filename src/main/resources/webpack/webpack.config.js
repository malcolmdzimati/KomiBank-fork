const autoprefixer = require('autoprefixer');
const path = require('path');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin'); // replaces ExtractTextPlugin

module.exports = {
	entry: {
		app: './src/app.js'
	},
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: '../../static/js/[name].js'
	},
	module: {
		rules: [
			// sass-loader with sourceMap activated
			{
				test: /\.scss$/,
				include: [path.resolve(__dirname, 'src', 'sass')],
				use: [
					MiniCssExtractPlugin.loader, // Use MiniCssExtractPlugin instead of ExtractTextPlugin
					{
						loader: 'css-loader',
						options: {
							sourceMap: true,
							url: false
						}
					},
					{
						loader: 'postcss-loader',
						options: {
							postcssOptions: {
								plugins: [autoprefixer()]
							},
							sourceMap: true
						},
					},
					{
						loader: 'sass-loader',
						options: {
							sourceMap: true,
							implementation: require("sass-embedded"),
							sassOptions: {
								includePaths: ['./node_modules']
							}
						},
					}
				],
			},
			
			{
				test: /\.js$/,
				loader: 'babel-loader',
				options: {
					presets: ['@babel/preset-env'],
					plugins: ['@babel/plugin-transform-object-assign']
				},
			}
		],
	},

	plugins: [
		// MiniCssExtractPlugin instance
		new MiniCssExtractPlugin({
			filename: '../../static/css/[name].css'
		}),
	],

	watch: true
};