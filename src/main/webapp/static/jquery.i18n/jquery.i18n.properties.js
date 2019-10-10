/******************************************************************************
 *                jquery.i18n.properties.js hacking
 * 说明：
 *     jquery.i18n.properties本来是用来做多语言适配的，想基于他完成自动迭代语言
 * 更换，所以直接解读其源代码，从中知道JavaScript库的写法，了解template的工作原理，
 * 函数、变量合成等等，源代码只有500来行，很精练。
 *
 *                                            2017-8-31 深圳 龙华樟坑村 曾剑锋
 *****************************************************************************/

// source code:
//     https://github.com/jquery-i18n-properties/jquery-i18n-properties/blob/master/jquery.i18n.properties.js

/******************************************************************************
 * jquery.i18n.properties
 *
 * Dual licensed under the GPL (http://dev.jquery.com/browser/trunk/jquery/GPL-LICENSE.txt) and
 * MIT (http://dev.jquery.com/browser/trunk/jquery/MIT-LICENSE.txt) licenses.
 *
 * @version     1.2.7
 * @url         https://github.com/jquery-i18n-properties/jquery-i18n-properties
 * @inspiration Localisation assistance for jQuery (http://keith-wood.name/localisation.html)
 *              by Keith Wood (kbwood{at}iinet.com.au) June 2007
 *
 *****************************************************************************/

(function ($) {

    // 声明对象
    $.i18n = {};

    /**
     * Map holding bundle keys if mode is 'map' or 'both'. Values of this can also be an
     * Object, in which case the key is a namespace.
     */
    // 声明对象
    $.i18n = {};
    $.i18n.map = {};

    // 定义debug函数
    var debug = function (message) {
        window.console && console.log('i18n::' + message);
    };

    /**
     * Load and parse message bundle files (.properties),
     * making bundles keys available as javascript variables.
     *
     * i18n files are named <name>.js, or <name>_<language>.js or <name>_<language>_<country>.js
     * Where:
     *      The <language> argument is a valid ISO Language Code. These codes are the lower-case,
     *      two-letter codes as defined by ISO-639. You can find a full list of these codes at a
     *      number of sites, such as: http://www.loc.gov/standards/iso639-2/englangn.html
     *      The <country> argument is a valid ISO Country Code. These codes are the upper-case,
     *      two-letter codes as defined by ISO-3166. You can find a full list of these codes at a
     *      number of sites, such as: http://www.iso.ch/iso/en/prods-services/iso3166ma/02iso-3166-code-lists/list-en1.html
     *
     * Sample usage for a bundles/Messages.properties bundle:
     * $.i18n.properties({
     *      name:      'Messages',
     *      language:  'en_US',
     *      path:      'bundles'
     * });
     * @param  name      (string/string[], optional) names of file to load (eg, 'Messages' or ['Msg1','Msg2']). Defaults to "Messages"
     * @param  language    (string, optional) language/country code (eg, 'en', 'en_US', 'pt_BR'). if not specified, language reported by the browser will be used instead.
     * @param  path      (string, optional) path of directory that contains file to load
     * @param  mode      (string, optional) whether bundles keys are available as JavaScript variables/functions or as a map (eg, 'vars' or 'map')
     * @param  debug     (boolean, optional) whether debug statements are logged at the console
     * @param  cache        (boolean, optional) whether bundles should be cached by the browser, or forcibly reloaded on each page load. Defaults to false (i.e. forcibly reloaded)
     * @param  encoding  (string, optional) the encoding to request for bundles. Property file resource bundles are specified to be in ISO-8859-1 format. Defaults to UTF-8 for backward compatibility.
     * @param  callback     (function, optional) callback function to be called after script is terminated
     */
    // properties函数，传入json配置
    $.i18n.properties = function (settings) {

        var defaults = {
            name: 'Messages',
            language: '',
            path: '',
            namespace: null,
            mode: 'vars',
            cache: false,
            debug: false,
            encoding: 'UTF-8',
            async: false,
            callback: null
        };

        // 获取合并的配置信息，defaults中的值和参数传递的值合并，相同的key，采用settings中的值；
        settings = $.extend(defaults, settings);

        // 检查命名空间，以命名空间值作为key，创建存储对象
        if (settings.namespace && typeof settings.namespace == 'string') {
            // A namespace has been supplied, initialise it.
            if (settings.namespace.match(/^[a-z]*$/)) {
                $.i18n.map[settings.namespace] = {};
            } else {
                debug('Namespaces can only be lower case letters, a - z');
                settings.namespace = null;
            }
        }

        // Ensure a trailing slash on the path
        if (!settings.path.match(/\/$/)) settings.path += '/';

        // 自动获取浏览器当前的语言，如果需要单独用到这个函数，需要传入：{"language" : ""}，防止报错；
        // Try to ensure that we have at a least a two letter language code
        settings.language = this.normaliseLanguageCode(settings);

        // Ensure an array
        // 如果只是一个字符串，将字符串转换成数组，方便迭代
        var files = (settings.name && settings.name.constructor === Array) ? settings.name : [settings.name];

        // A locale is at least a language code which means at least two files per name. If
        // we also have a country code, thats an extra file per name.
        settings.totalFiles = (files.length * 2) + ((settings.language.length >= 5) ? files.length : 0);
        if (settings.debug) {
            debug('totalFiles: ' + settings.totalFiles);
        }

        // 计数
        settings.filesLoaded = 0;

        // 根据name数组迭代，每一个可能有2或者3个文件
        files.forEach(function (file) {

            var defaultFileName, shortFileName, longFileName, fileNames;
            // 1. load base (eg, Messages.properties)
            defaultFileName = settings.path + file + '.properties';
            // 2. with language code (eg, Messages_pt.properties)
            var shortCode = settings.language.substring(0, 2);
            shortFileName = settings.path + file + '_' + shortCode + '.properties';
            // 3. with language code and country code (eg, Messages_pt_BR.properties)
            if (settings.language.length >= 5) {
                var longCode = settings.language.substring(0, 5);
                longFileName = settings.path + file + '_' + longCode + '.properties';
                //fileNames = [defaultFileName, shortFileName, longFileName];
                fileNames = [longFileName];
            } else {
                fileNames = [defaultFileName, shortFileName];
            }
            // 加载文件
            loadAndParseFiles(fileNames, settings);
        });

        // call callback
        if (settings.callback && !settings.async) {
            settings.callback();
        }
    }; // properties

    /**
     * When configured with mode: 'map', allows access to bundle values by specifying its key.
     * Eg, jQuery.i18n.prop('com.company.bundles.menu_add')
     */
    $.i18n.prop = function (key /* Add parameters as function arguments as necessary  */) {

        // [].slice.call(arguments) 是如何工作的
        // https://egoist.moe/2015/12/13/how-does-array-slice-call-arguments-work/
        var args = [].slice.call(arguments);

        var phvList, namespace;
        // jQuery.i18n.prop('msg_complex', 'John');  
        // 主要是为了处理这种可能，也就是为了解决带有占位符的配置
        if (args.length == 2) {
            if ($.isArray(args[1])) {
                // An array was passed as the second parameter, so assume it is the list of place holder values.
                phvList = args[1];
            } else if (typeof args[1] === 'object') {
                // Second argument is an options object {namespace: 'mynamespace', replacements: ['egg', 'nog']}
                // 上面是一个参数示例，应该说也是一个传递标准
                namespace = args[1].namespace;
                var replacements = args[1].replacements;
                // JavaScript Array splice() Method
                // https://www.w3schools.com/jsref/jsref_splice.asp
                args.splice(-1, 1);             // 这里应该是移除最后一个参数，也就是移除arg[1]
                if (replacements) {
                    // push() 方法将一个或多个元素添加到数组的末尾，并返回数组的新长度。
                    // https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/push
                    Array.prototype.push.apply(args, replacements);
                }
            }
        }

        // 没有命名空间就直接取值，有命名空间就在命名空间下取值，没有值，那就不用解析了
        var value = (namespace) ? $.i18n.map[namespace][key] : $.i18n.map[key];
        if (value === null) {
            return '[' + ((namespace) ? namespace + '#' + key : key) + ']';
        }

        // Place holder replacement
        // 替换占位符
        /**
        * Tested with:
        *   test.t1=asdf ''{0}''
        *   test.t2=asdf '{0}' '{1}'{1}'zxcv
        *   test.t3=This is \"a quote" 'a''{0}''s'd{fgh{ij'
        *   test.t4="'''{'0}''" {0}{a}
        *   test.t5="'''{0}'''" {1}
        *   test.t6=a {1} b {0} c
        *   test.t7=a 'quoted \\ s\ttringy' \t\t x
        *
        * Produces:
        *   test.t1, p1 ==> asdf 'p1'
        *   test.t2, p1 ==> asdf {0} {1}{1}zxcv
        *   test.t3, p1 ==> This is "a quote" a'{0}'sd{fgh{ij
        *   test.t4, p1 ==> "'{0}'" p1{a}
        *   test.t5, p1 ==> "'{0}'" {1}
        *   test.t6, p1 ==> a {1} b p1 c
        *   test.t6, p1, p2 ==> a p2 b p1 c
        *   test.t6, p1, p2, p3 ==> a p2 b p1 c
        *   test.t7 ==> a quoted \ s    tringy          x
        */

        var i;
        if (typeof(value) == 'string') {
            // Handle escape characters. Done separately from the tokenizing loop below because escape characters are
            // active in quoted strings.
            // 转义字符转换为实际字符
            i = 0;
            while ((i = value.indexOf('\\', i)) != -1) {
                if (value.charAt(i + 1) == 't') {
                    value = value.substring(0, i) + '\t' + value.substring((i++) + 2); // tab
                } else if (value.charAt(i + 1) == 'r') {
                    value = value.substring(0, i) + '\r' + value.substring((i++) + 2); // return
                } else if (value.charAt(i + 1) == 'n') {
                    value = value.substring(0, i) + '\n' + value.substring((i++) + 2); // line feed
                } else if (value.charAt(i + 1) == 'f') {
                    value = value.substring(0, i) + '\f' + value.substring((i++) + 2); // form feed
                } else if (value.charAt(i + 1) == '\\') {
                    value = value.substring(0, i) + '\\' + value.substring((i++) + 2); // \
                } else {
                    value = value.substring(0, i) + value.substring(i + 1); // Quietly drop the character
                }
            }

            // Lazily convert the string to a list of tokens.
            // 这里主要是处理被''引起的部分字符串，因为''引起来的字符串不转义，直接原样输出
            // 去掉单引号和花括号，单引号中的内容不转义，花括号中的值是函数执行形参索引
            var arr = [], j, index;
            i = 0;
            while (i < value.length) {
                if (value.charAt(i) == '\'') {
                    // Handle quotes
                    if (i == value.length - 1) {        // 最后一个字符，丢弃掉
                        value = value.substring(0, i);  // Silently drop the trailing quote
                    } else if (value.charAt(i + 1) == '\'') {   // 处理''挨在一起的情况，直接去掉''
                        value = value.substring(0, i) + value.substring(++i); // Escaped quote
                    } else {
                        // Quoted string
                        j = i + 2;
                        // indexOf() 方法可返回某个指定的字符串值在字符串中首次出现的位置。
                        while ((j = value.indexOf('\'', j)) != -1) {
                            if (j == value.length - 1 || value.charAt(j + 1) != '\'') {
                                // Found start and end quotes. Remove them
                                // 前面的i算是start，这里的单引号算是结束
                                value = value.substring(0, i) + value.substring(i + 1, j) + value.substring(j + 1);
                                i = j - 1;
                                break;
                            } else {
                                // Found a double quote, reduce to a single quote.
                                // 又发现了两个单引号，当一个单引号用，并且继续循环
                                // 如"'zengjf''zengjf'"
                                value = value.substring(0, j) + value.substring(++j);
                            }
                        }

                        if (j == -1) {
                            // There is no end quote. Drop the start quote
                            // 不存在结束单引号的情况
                            value = value.substring(0, i) + value.substring(i + 1);
                        }
                    }
                } else if (value.charAt(i) == '{') {        // 动态生成替换占位符
                    // Beginning of an unquoted place holder.
                    j = value.indexOf('}', i + 1);
                    if (j == -1) {
                        i++; // No end. Process the rest of the line. Java would throw an exception
                    } else {
                        // Add 1 to the index so that it aligns with the function arguments.
                        index = parseInt(value.substring(i + 1, j));
                        if (!isNaN(index) && index >= 0) {
                            // Put the line thus far (if it isn't empty) into the array
                            var s = value.substring(0, i);
                            if (s !== "") {
                                arr.push(s);
                            }
                            // Put the parameter reference into the array
                            arr.push(index);
                            // Start the processing over again starting from the rest of the line.
                            i = 0;
                            value = value.substring(j + 1);    // value截取剩余部分，这样arr中的字符串就是唯一的了
                        } else {
                            i = j + 1; // Invalid parameter. Leave as is.
                        }
                    }
                } else {
                    i++;        // 既不是单引号，也不是花括号
                }
            } // while

            // Put the remainder of the no-empty line into the array.
            if (value !== "") {
                arr.push(value);
            }
            value = arr;

            // Make the array the value for the entry.
            if (namespace) {
                $.i18n.map[settings.namespace][key] = arr;
            } else {
                $.i18n.map[key] = arr;
            }
        }

        if (value.length === 0) {
            return "";
        }
        if (value.length == 1 && typeof(value[0]) == "string") {
            return value[0];
        }

        // 前面已经根据{?}占位符截取了成了数组了，现在要用参数合成String输出
        var str = "";
        for (i = 0, j = value.length; i < j; i++) {
            if (typeof(value[i]) == "string") {                     // 字符串，就取value中的值
                str += value[i];
            } else if (phvList && value[i] < phvList.length) {      // 参数替代
                // Must be a number
                str += phvList[value[i]];
            } else if (!phvList && value[i] + 1 < args.length) {    
                str += args[value[i] + 1];
            } else {
                str += "{" + value[i] + "}";
            }
        }

        return str;
    };

    function callbackIfComplete(settings) {

        if (settings.debug) {
            debug('callbackIfComplete()');
            debug('totalFiles: ' + settings.totalFiles);
            debug('filesLoaded: ' + settings.filesLoaded);
        }

        if (settings.async) {
            if (settings.filesLoaded === settings.totalFiles) {
                if (settings.callback) {
                    settings.callback();
                }
            }
        }
    }

    function loadAndParseFiles(fileNames, settings) {

        if (settings.debug) debug('loadAndParseFiles');

        if (fileNames !== null && fileNames.length > 0) {
            loadAndParseFile(fileNames[0], settings, function () {
                // shift() 方法用于把数组的第一个元素从其中删除，并返回第一个元素的值。
                fileNames.shift();
                loadAndParseFiles(fileNames,settings);      // 递归处理
            });
        } else {
            // 是否同步回调
            callbackIfComplete(settings);
        }
    }

    /** Load and parse .properties files */
    function loadAndParseFile(filename, settings, nextFile) {

        // 加载的文件名，总共要加载多少，当前加载了多少
        if (settings.debug) {
            debug('loadAndParseFile(\'' + filename +'\')');
            debug('totalFiles: ' + settings.totalFiles);
            debug('filesLoaded: ' + settings.filesLoaded);
        }

        // ajax直接获取文件
          if (filename !== null && typeof filename !== 'undefined') {
            $.ajax({
                url: filename,
                async: settings.async,
                cache: settings.cache,
                dataType: 'text',
                success: function (data, status) {

                    if (settings.debug) {
                        debug('Succeeded in downloading ' + filename + '.');
                        debug(data);        // 输出文件内容
                    }

                    // 解析获取的数据
                    parseData(data, settings);
                    nextFile();
                },
                error: function (jqXHR, textStatus, errorThrown) {

                    if (settings.debug) {
                        debug('Failed to download or parse ' + filename + '. errorThrown: ' + errorThrown);
                    }
                    if (jqXHR.status === 404) {
                        settings.totalFiles -= 1;
                    }
                    nextFile();
                }
            });
        }
    }

    /** Parse .properties files */
    function parseData(data, settings) {

        var parsed = '';
        var lines = data.split(/\n/);               // 数据以一行一行解析
        var regPlaceHolder = /(\{\d+})/g;           // 截取每一行占位符
        var regRepPlaceHolder = /\{(\d+)}/g;        // 截取每一行占位符
        var unicodeRE = /(\\u.{4})/ig;
        for (var i=0,j=lines.length;i<j;i++) {
            var line = lines[i];                    // 第i行

            line = line.trim();                     // 去掉行头，行尾空白字符
            // 非空白行，且不是以#号开始的行
            if (line.length > 0 && line.match("^#") != "#") { // skip comments
                var pair = line.split('=');         // 等号分割
                if (pair.length > 0) {
                    /** Process key & value */
                    // 获取key、value，可能value是不存在，那就给""字符串
                    var name = decodeURI(pair[0]).trim();
                    var value = pair.length == 1 ? "" : pair[1];
                    // process multi-line values        
                    // 支持多行value值，用\换行
                    while (value.search(/\\$/) != -1) {
                        value = value.substring(0, value.length - 1);
                        value += lines[++i].trimRight();
                    }
                    // Put values with embedded '='s back together
                    // 允许value中存在=，也就是忽视第二个及以后的=号
                    for (var s = 2; s < pair.length; s++) {
                        value += '=' + pair[s];
                    }
                    value = value.trim();               // 移除value中前后的空白字符

                    /** Mode: bundle keys in a map */
                    if (settings.mode == 'map' || settings.mode == 'both') {
                        // handle unicode chars possibly left out
                        var unicodeMatches = value.match(unicodeRE);
                        if (unicodeMatches) {
                            unicodeMatches.forEach(function (match) {
                                value = value.replace(match, unescapeUnicode(match));
                            });
                        }
                        // add to map
                        // 有这里可以看出，key、value存在了$.i18n.map中，如果需要自动迭代，
                        // 那么就可以去迭代这里的key，这也是跟这份源代码的初衷吧
                        if (settings.namespace) {
                            $.i18n.map[settings.namespace][name] = value;
                        } else {
                            $.i18n.map[name] = value;
                        }
                    }

                    /** Mode: bundle keys as vars/functions */
                    if (settings.mode == 'vars' || settings.mode == 'both') {
                        // 转义双引号，将value中的双引号换成\双引号
                        value = value.replace(/"/g, '\\"'); // escape quotation mark (")

                        // make sure namespaced key exists (eg, 'some.key')
                        checkKeyNamespace(name);

                        // value with variable substitutions
                        // 检查是否有占位符
                        if (regPlaceHolder.test(value)) {
                            // 分隔出类似{?}这种占位符，这里由于()的存在，并不会丢失{?}
                            //  "Good morning = {0}! = {1}fasdfasdf = {2} fasdf.\n"; --> ["Good morning = ", "{0}", "! = ", "{1}", "fasdfasdf = ", "{2}", " fasdf.\n"]
                            var parts = value.split(regPlaceHolder);
                            // process function args
                            var first = true;
                            var fnArgs = '';
                            var usedArgs = [];
                            parts.forEach(function (part) {

                                // 选择出{?}进行处理；
                                // 第一次进行处理usedArgs.length为0
                                // usedArgs中不存在当前的匹配值
                                if (regPlaceHolder.test(part) && (usedArgs.length === 0 || usedArgs.indexOf(part) == -1)) {
                                    // 第一次的不算
                                    if (!first) {
                                        fnArgs += ',';
                                    }
                                    // v0,v1,v2
                                    // 这里的$1表示获取regRepPlaceHolder正则表达式中的()好中的值，正好是0,1,
                                    fnArgs += part.replace(regRepPlaceHolder, 'v$1');
                                    usedArgs.push(part);
                                    first = false;
                                }
                            });
                            // 函数字符串合成，name为函数名，fnArgs为参数列表
                            parsed += name + '=function(' + fnArgs + '){';
                            // process function body
                            // Good morning "+v0+"! "+v1+"fasdfasdf "+v2+" fasdf.
                            // 注意这里会把{}去掉的因为只用了$1，只会取()一级匹配的数值
                            var fnExpr = '"' + value.replace(regRepPlaceHolder, '"+v$1+"') + '"';
                            parsed += 'return ' + fnExpr + ';' + '};';
                            // simple value
                        } else {
                            // 最简单的key = value；
                            parsed += name + '="' + value + '";';
                        }
                    } // END: Mode: bundle keys as vars/functions
                } // END: if(pair.length > 0)
            } // END: skip comments
        }
        eval(parsed);               // 获取变量、函数，一个文件执行一次，而不是一行执行一次
        settings.filesLoaded += 1;
    }

    /** Make sure namespace exists (for keys with dots in name) */
    // TODO key parts that start with numbers quietly fail. i.e. month.short.1=Jan
    function checkKeyNamespace(key) {

        var regDot = /\./;
        // 一个在字符串中测试是否匹配的RegExp方法，它返回true或false。
        if (regDot.test(key)) {
            var fullname = '';
            var names = key.split(/\./);
            for (var i=0,j=names.length;i<j;i++) {
                var name = names[i];

                // 第一次不加，第一次就是直接执行下面的fullname += name;
                if (i > 0) {
                    fullname += '.';
                }

                fullname += name;
                // 每次不存在就重新eval生成变量
                if (eval('typeof ' + fullname + ' == "undefined"')) {
                    eval(fullname + '={};');
                }
            }
        }
    }

    /** Ensure language code is in the format aa_AA. */
    $.i18n.normaliseLanguageCode = function (settings) {

        var lang = settings.language;
        if (!lang || lang.length < 2) {
            if (settings.debug) debug('No language supplied. Pulling it from the browser ...');
            lang = (navigator.languages && navigator.languages.length > 0) ? navigator.languages[0]
                                        : (navigator.language || navigator.userLanguage /* IE */ || 'en');
            if (settings.debug) debug('Language from browser: ' + lang);
        }

        lang = lang.toLowerCase();
        lang = lang.replace(/-/,"_"); // some browsers report language as en-US instead of en_US
        if (lang.length > 3) {
            lang = lang.substring(0, 3) + lang.substring(3).toUpperCase();
        }
        return lang;
    };

    /** Unescape unicode chars ('\u00e3') */
    function unescapeUnicode(str) {

        // unescape unicode codes
        var codes = [];
        var code = parseInt(str.substr(2), 16);
        if (code >= 0 && code < Math.pow(2, 16)) {
            codes.push(code);
        }
        // convert codes to text
        return codes.reduce(function (acc, val) { return acc + String.fromCharCode(val); }, '');
    }
}) (jQuery);