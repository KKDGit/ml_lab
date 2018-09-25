from setuptools import setup

setup(name='amaze',
      version='0.2',
      description='Maze generation',
      author='Reindert-Jan Ekker',
      author_email='info@rjekker.nl',
      packages=['amaze', 'amaze.demo'],
      entry_points={
          'console_scripts': [
              'amaze_demo=amaze.demo.tkdemo:main',
          ],
      },
)

__author__ = 'Reindert-Jan Ekker'
