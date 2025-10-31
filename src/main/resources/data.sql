-- ===========================
-- ✅ КАТЕГОРИИ
-- ===========================

INSERT INTO category (id, slug, title)
SELECT 1, 'zakuski', 'Закуски'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=1);

INSERT INTO category (id, slug, title)
SELECT 2, 'pasta', 'Паста'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=2);

INSERT INTO category (id, slug, title)
SELECT 3, 'mains', 'Основные блюда'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=3);

INSERT INTO category (id, slug, title)
SELECT 4, 'pizza', 'Пицца'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=4);

INSERT INTO category (id, slug, title)
SELECT 5, 'desserts', 'Десерты'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=5);

INSERT INTO category (id, slug, title)
SELECT 6, 'drinks', 'Напитки'
WHERE NOT EXISTS (SELECT 1 FROM category WHERE id=6);

-- ===========================
-- ✅ ЗАКУСКИ (id = 1)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 1,'Брускетта с томатами и базиликом','Чиабатта, помидоры, базилик, оливковое масло, чеснок',320,195,5,8,25
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Брускетта с томатами и базиликом');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 1,'Антипасто-микс','Ассорти сыров, прошутто, салями, оливки, овощи гриль',890,460,28,30,14
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Антипасто-микс');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 1,'Капрезе','Моцарелла, помидоры, базилик, оливковое масло',450,260,12,18,6
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Капрезе');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 1,'Фритто Мисто','Кальмары, креветки, мини-осьминоги, лимон',950,520,28,26,40
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Фритто Мисто');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 1,'Карпаччо из говядины','Говядина, пармезан, руккола, каперсы, оливковое масло, лимонный сок',980,210,20,12,2
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Карпаччо из говядины');

-- ===========================
-- ✅ ПАСТА (id = 2)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 2,'Спагетти Карбонара','Спагетти, гуанчале, яйцо, пармезан, чёрный перец',680,520,21,22,56
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Спагетти Карбонара');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 2,'Равиоли с рикоттой и шпинатом','Равиоли, рикотта, шпинат, сливочное масло, шалфей',740,430,16,18,48
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Равиоли с рикоттой и шпинатом');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 2,'Лазанья Болоньезе','Паста, соус болоньезе, говядина, свинина, бешамель, пармезан',790,590,30,28,52
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Лазанья Болоньезе');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 2,'Лингвини с морепродуктами','Лингвини, мидии, креветки, кальмары, томатный соус, белое вино',980,510,32,16,58
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Лингвини с морепродуктами');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 2,'Ризотто с белыми грибами','Рис арборио, белые грибы, овощной бульон, пармезан, сливочное масло',820,470,15,16,32
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Ризотто с белыми грибами');

-- ===========================
-- ✅ ПИЦЦА (id = 4)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 4,'Маргарита','Томатный соус, моцарелла, базилик, оливковое масло',690,720,25,28,85
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Маргарита');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 4,'Дьявола','Моцарелла, томатный соус, салями пиканте, орегано',890,880,36,38,92
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Дьявола');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 4,'Четыре сыра','Моцарелла, горгонзола, пармезан, пекорино, сливочный соус',920,950,35,45,90
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Четыре сыра');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 4,'Пицца с прошутто и рукколой','Моцарелла, томаты, прошутто, руккола, пармезан',950,860,34,32,98
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Пицца с прошутто и рукколой');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 4,'Капричоза','Томатный соус, моцарелла, артишоки, ветчина, шампиньоны, оливки',890,860,32,35,95
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Капричоза');

-- ===========================
-- ✅ ОСНОВНЫЕ БЛЮДА (id = 3)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 3,'Дорадо на гриле с лимоном и травами','Дорада, лимон, розмарин, оливковое масло',980,410,40,22,0
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Дорадо на гриле с лимоном и травами');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 3,'Курица по-римски','Куриное бедро, томаты, перец, оливковое масло, белое вино',890,470,34,24,8
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Курица по-римски');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 3,'Стейк Флорентийский','Говядина T-бон, розмарин, оливковое масло, морская соль',2200,1150,82,85,2
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Стейк Флорентийский');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 3,'Тунец на гриле с овощами','Тунец, овощи гриль, оливковое масло',1180,430,44,22,6
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Тунец на гриле с овощами');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 3,'Оссобуко по-милански','Телячья голень, белое вино, лук, морковь, сельдерей, томаты, оливковое масло',1250,560,42,28,14
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Оссобуко по-милански');

-- ===========================
-- ✅ ДЕСЕРТЫ (id = 5)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 5,'Панна котта с ягодным соусом','Сливки, желатин, ваниль, ягодный соус',420,280,6,20,18
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Панна котта с ягодным соусом');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 5,'Канноли с рикоттой и фисташками','Тонкий хрустящий рулетик, крем из рикотты, фисташки',390,310,8,18,28
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Канноли с рикоттой и фисташками');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 5,'Джелато','Домашнее мороженое (ваниль, фисташка, шоколад)',350,240,6,10,28
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Джелато');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 5,'Семифреддо с миндалём','Полумороженый десерт на основе сливок и орехов',430,320,7,20,28
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Семифреддо с миндалём');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 5,'Тирамису','Савоярди, маскарпоне, яйцо, кофе эспрессо, какао-порошок',450,390,8,24,30
WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Тирамису');

-- ===========================
-- ✅ НАПИТКИ (id = 6)
-- ===========================
INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Клюквенный чай','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Клюквенный чай');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Малиновый чай','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Малиновый чай');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Облепиховый чай','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Облепиховый чай');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Тропический чай','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Тропический чай');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Листовой чай в ассортименте','',350,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Листовой чай в ассортименте');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Американо','',320,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Американо');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Капучино','',330,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Капучино');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Латте','',390,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Латте');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Мокко','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Мокко');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Раф в ассортименте','',480,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Раф в ассортименте');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Мохито','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Мохито');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Пина колада','',450,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Пина колада');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Лимонад','',350,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Лимонад');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Милкшейк','',380,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Милкшейк');

INSERT INTO dish (category_id, title, description, price, kcal, proteins, fats, carbs)
SELECT 6,'Сок в ассортименте','',250,0,0,0,0 WHERE NOT EXISTS (SELECT 1 FROM dish WHERE title='Сок в ассортименте');
